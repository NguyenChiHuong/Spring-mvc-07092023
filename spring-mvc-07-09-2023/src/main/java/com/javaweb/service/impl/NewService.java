package com.javaweb.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaweb.converter.NewConverter;
import com.javaweb.dto.NewDTO;
import com.javaweb.entity.CategoryEntity;
import com.javaweb.entity.NewEntity;
import com.javaweb.repository.CategoryRepository;
import com.javaweb.repository.NewRepository;
import com.javaweb.service.INewService;
import com.javaweb.util.UploadFileUtils;

@Service
public class NewService implements INewService {
	
	@Autowired
	private NewRepository newRepository;
	
	@Autowired
	private NewConverter newConverter;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private UploadFileUtils uploadFileUtils;
	
	@Override
	public List<NewDTO> findAll(Pageable pageable) {
		List<NewDTO> models = new ArrayList<>();
		List<NewEntity> entities = newRepository.findAll(pageable).getContent();//select all item news from database put on list
		for(NewEntity item : entities) {
			NewDTO dto = newConverter.toDto(item);//convert dto to entity post on client
			models.add(dto);
		}
		return models;
	}

	@Override
	public int getTotalItem() {
		return (int) newRepository.count();//đếm số phần tử có trong database
	}

	@Override
	public NewDTO findById(long id) {
		NewEntity newEntity = newRepository.findOne(id);
		return newConverter.toDto(newEntity);
	}

	@Override
	@Transactional//Use for both commit and rollback
	public NewDTO save(NewDTO dto) {
	
		byte[] decodeBase64 = java.util.Base64.getDecoder().decode(dto.getThumbnailBase64().getBytes());
        
		try (InputStream thumbnailStream = new ByteArrayInputStream(decodeBase64)) {
			byte[] thumbnailBytes = readAllBytes(thumbnailStream);
			String thumbnailPath = dto.getThumbnail();
			uploadFileUtils.writeOrUpdate(thumbnailPath, thumbnailBytes);
			dto.setThumbnail(thumbnailPath);
		} catch (IOException e) {
			// Handle or log the exception appropriately
			e.printStackTrace();
		}
		CategoryEntity category = categoryRepository.findOneByCode(dto.getCategoryCode()); //get categorycode from client
		NewEntity newEntity = new NewEntity();
		if(dto.getId() != null) {
			NewEntity oldnew = newRepository.findOne(dto.getId()); // select one new throw id get from client
			newEntity = newConverter.toEntity(oldnew, dto);
		}else {
			newEntity = newConverter.toEntity(dto); //convert from dto to entity 
		}
		newEntity.setCategory(category); //set name category throw code category
		//dto.setThumbnail(dirDefault + "/"+ dto.getThumbnail());
		//saveImage(dto, newEntity);
		return newConverter.toDto(newRepository.save(newEntity)); //insert or update to database and convert put/post on client 
	}
	
	@Override
	@Transactional
	public void delete(long[] ids) {
		for (long id : ids) {
			newRepository.delete(id);
		}
	}

	private byte[] readAllBytes(InputStream inputStream) throws IOException {
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    byte[] buffer = new byte[1024]; // Kích thước ban đầu
	    int bytesRead;
	    while ((bytesRead = inputStream.read(buffer)) != -1) {
	        baos.write(buffer, 0, bytesRead);

	        // Kiểm tra kích thước file và điều chỉnh buffer nếu cần
	        if (bytesRead == buffer.length) {
	            int newBufferSize = calculateNewBufferSize(buffer.length);
	            buffer = new byte[newBufferSize];
	        }
	    }
	    return baos.toByteArray();
	}

	private int calculateNewBufferSize(int currentBufferSize) {
	    // Bạn có thể triển khai logic tùy thuộc vào yêu cầu của ứng dụng
	    // Ví dụ: Tăng gấp đôi kích thước buffer cho mỗi điều chỉnh
	    return currentBufferSize * 2;
	}
	
	/*@Override
	public void saveImage(NewDTO newDTO, NewEntity newEntity) {
		String path = "/popup/" + newDTO.getImageName();
        if (null != newDTO.getThumbnail()) {
            if (null != newDTO.getThumbnail()) {
                if (!path.equals(newEntity.getThumbnail())) {
                    File file = new File(dirDefault + newEntity.getThumbnail());
                    file.delete();
                }
            }
            byte[] bytes = Base64.decodeBase64(newDTO.getThumbnail().getBytes());
            uploadFileUtils.writeOrUpdate(path, bytes);
            newEntity.setThumbnail(path);
        }
	}*/

}
