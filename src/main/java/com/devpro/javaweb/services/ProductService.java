package com.devpro.javaweb.services;

import java.io.File;
import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.devpro.javaweb.dto.ByCategory;
import com.devpro.javaweb.dto.CustomerSearch;
import com.devpro.javaweb.dto.ProductSearchModel;
import com.devpro.javaweb.model.Product;
import com.devpro.javaweb.model.ProductImages;
import com.github.slugify.Slugify;


@Service
public class ProductService extends BaseService<Product>{
	@Autowired
	private ProductImagesService productImagesService;
	@Override
	protected Class<Product> clazz() {
		// TODO Auto-generated method stub
		return Product.class;
	}
	/**
	 * dùng để kiểm tra xem admin có upload ảnh product hay không
	 * @param images
	 * @return
	 */
	private boolean isEmptyUploadFile(MultipartFile[] images) {
		if (images == null || images.length <= 0)
			return true;

		if (images.length == 1 && images[0].getOriginalFilename().isEmpty())
			return true;

		return false;
	}

	/**
	 * dùng để kiểm tra xem admin có upload ảnh product hay không
	 * @param image
	 * @return
	 */
	private boolean isEmptyUploadFile(MultipartFile image) {
		return image == null || image.getOriginalFilename().isEmpty();
	}
	
	/**
	 * Chú ý: trong các hàm ở tầng Service dùng để thêm mới hoặc xóa hoặc chỉnh sửa cần thêm @Transactional.
	 * Thêm mới sản phẩm.
	 * @param p
	 * @param productAvatar
	 * @param productPictures
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	private String getUniqueUploadFileName(String fileName) {
		String[] splitFileName = fileName.split("\\.");
		return splitFileName[0] + System.currentTimeMillis() + "." + splitFileName[1];
	}
	@Transactional
	public Product add(Product p, MultipartFile productAvatar, MultipartFile[] productPictures)
			throws IllegalStateException, IOException {

		// kiểm tra xem admin có đẩy avatar lên không ???
		if (!isEmptyUploadFile(productAvatar)) { // có đẩy avatar lên.
			
			// tạo đường dẫn tới folder chứa avatar
			String pathToAvatar = "upload/product/avatar/" + productAvatar.getOriginalFilename();

			// lưu avatar vào đường dẫn trên
			productAvatar.transferTo(new File(pathToAvatar));

			p.setAvatar("product/avatar/" + productAvatar.getOriginalFilename());
		}

		// có đẩy pictures(product_images) ???
		if (!isEmptyUploadFile(productPictures)) { // có đẩy pictures lên.

			// nếu admin đẩy lên thì duyệt tất cả file đẩy lên và lưu trên server
			for (MultipartFile pic : productPictures) {
				
				// lưu ảnh admin đẩy lên vào server
				pic.transferTo(new File("upload/product/pictures/" + pic.getOriginalFilename()));

				// tạo mới 1 bản ghi product_images
				ProductImages pi = new ProductImages();
				pi.setPath("product/pictures/" + pic.getOriginalFilename());
				pi.setTitle(pic.getOriginalFilename());

				p.addProductImages(pi);
			}
		}

		// tạo seo
		p.setSeo(new Slugify().slugify(p.getTitle() + "-" + System.currentTimeMillis()));
		
		// lưu vào database
		return super.saveOrUpdate(p);
		
	}
	
	@Transactional
	public Product update(Product p, MultipartFile productAvatar, MultipartFile[] productPictures)
			throws IllegalStateException, IOException {

		// lấy thông tin cũ của product theo id
		Product productInDb = super.getById(p.getId());

		// có đẩy avartar ??? => xóa avatar cũ đi và thêm avatar mới
		if (!isEmptyUploadFile(productAvatar)) {
			// xóa avatar trong folder lên
			new File("upload/" + productInDb.getAvatar()).delete();

			// sử dụng avatar mới
			productAvatar.transferTo(new File("upload/product/avatar/" + productAvatar.getOriginalFilename()));
			p.setAvatar("product/avatar/" + productAvatar.getOriginalFilename());
		} 
		else {
			// sử dụng lại avartar cũ
			p.setAvatar(productInDb.getAvatar());
		}

		// có đẩy pictures ???
		if (!isEmptyUploadFile(productPictures)) {

			// xóa pictures cũ
			if (productInDb.getProductImages() != null && productInDb.getProductImages().size() > 0) {
				for (ProductImages opi : productInDb.getProductImages()) {
					// xóa avatar trong folder lên
					new File("upload/" + opi.getPath()).delete();

					// xóa dữ liệu trong database
					productImagesService.delete(opi);
				}
			}

			// thêm pictures mới
			for (MultipartFile pic : productPictures) {
				pic.transferTo(new File("upload/product/pictures/" + pic.getOriginalFilename()));

				ProductImages pi = new ProductImages();
				pi.setPath("product/pictures/" + pic.getOriginalFilename());
				pi.setTitle(pic.getOriginalFilename());

				p.addProductImages(pi);
			}
		}

		//tạo seo
		p.setSeo(new Slugify().slugify(p.getTitle() + "-" + System.currentTimeMillis()));
		
		// lưu vào database
		return super.saveOrUpdate(p);
	}
	public PagerData<Product> search(ProductSearchModel searchModel) {

		// khởi tạo câu lệnh
		String sql = "SELECT * FROM tbl_products p WHERE 1 = 1";

		if (searchModel != null) {
			
//			 tìm kiếm theo category
			if(searchModel.getCategoryId() != null && searchModel.getCategoryId() > 0) {
				sql += " and category_id = " + searchModel.getCategoryId();
			}
		
			// tìm theo seo
//			if (!StringUtils.isEmpty(searchModel.seo)) {
//				sql += " and p.seo = '" + searchModel.seo + "'";
//			}

			// tìm kiếm sản phẩm hot
//			if (searchModel.isHot != null) {
//				sql += " and p.is_hot = '" + searchModel.seo + "'";
//			}
			
			// tìm kiếm theo title và description
			if (!StringUtils.isEmpty(searchModel.getKeyword())) {
				sql += " and (p.title like '%" + searchModel.getKeyword() + "%'" + 
						     " or p.detail_description like '%" + searchModel.getKeyword() + "%'" + 
						     " or p.short_description like '%" + searchModel.getKeyword() + "%')";
			}
		}

		// chi lay san pham chua xoa
//				sql += " and p.status=1 ";
		
		return getEntitiesByNativeSQL(sql, searchModel.getPage());
		
	}
	public PagerData<Product> search2(CustomerSearch searchModel) {

		// khởi tạo câu lệnh
		String sql = "SELECT * FROM tbl_products p WHERE p.status = 1";

		if (searchModel != null) {
			

			// tìm kiếm theo title và description
			if (!StringUtils.isEmpty(searchModel.getKeyword())) {
				sql += " and (p.title like '%" + searchModel.getKeyword() + "%'" + 
						     " or p.detail_description like '%" + searchModel.getKeyword() + "%'" + 
						     " or p.short_description like '%" + searchModel.getKeyword() + "%')";
			}
			if(searchModel.getSort_by()!=null){
				if(searchModel.getSort_by().equals("gia-cao-thap")) {
					sql += " order by p.price_sale desc";
				}
				else if (searchModel.getSort_by().equals("gia-thap-cao")) {
					sql += " order by p.price_sale asc";
				}
				else if(searchModel.getSort_by().equals("ban-chay")){
					sql = "select * from tbl_products P inner join (select S.product_id from tbl_saleorder_products S group by S.product_id order by sum(S.quality) desc) as E on P.id = E.product_id WHERE p.status = 1";
				}
			}
			if(searchModel.getSort_by()!=null&&!StringUtils.isEmpty(searchModel.getKeyword())&&searchModel.getSort_by().equals("ban-chay")) {
					sql = "select * from tbl_products P inner join (select S.product_id from tbl_saleorder_products S group by S.product_id order by sum(S.quality) desc) as E on P.id = E.product_id WHERE p.status = 1"
							+ " and (p.title like '%" + searchModel.getKeyword() + "%'" + 
							" or p.detail_description like '%" + searchModel.getKeyword() + "%'" + 
							" or p.short_description like '%" + searchModel.getKeyword() + "%')";
				}
			
		}

		// chi lay san pham chua xoa
//				sql += " and p.status=1 ";
		
		return getEntitiesByNativeSQL(sql, searchModel.getPage());
		
	}
	public PagerData<Product> sort(ByCategory searchModel) {

		// khởi tạo câu lệnh
		String sql = "SELECT * FROM tbl_products p WHERE p.status = 1";

		if (searchModel != null) {
			
			if(searchModel.getCategorySeo() != null) {
				sql += " and category_id = (select id from tbl_category where seo = '"+ searchModel.getCategorySeo()+"')";
			}
			if(searchModel.getSort_by()!=null){
				if(searchModel.getSort_by().equals("gia-cao-thap")) {
					sql += " order by p.price_sale desc";
				}
				else if (searchModel.getSort_by().equals("gia-thap-cao")) {
					sql += " order by p.price_sale asc";
				}
				else if(searchModel.getSort_by().equals("ban-chay")){
					sql = "select * from tbl_products P inner join (select S.product_id from tbl_saleorder_products S group by S.product_id order by sum(S.quality) desc) as E on P.id = E.product_id WHERE p.status = 1 and category_id = (select id from tbl_category where seo = '"+ searchModel.getCategorySeo()+"')";
				}
			}
			
		}

		// chi lay san pham chua xoa
//				sql += " and p.status=1 ";
		
		return getEntitiesByNativeSQL(sql, searchModel.getPage());
		
	}
}
