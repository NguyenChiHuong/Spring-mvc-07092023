package com.javaweb.dto;

public class NewDTO extends AbstractDTO<NewDTO> {
	
	private String title;
	private String thumbnail;
    private String thumbnailBase64;
	private String shortDescription;
	private String content;
	private Long categoryId;
	private String categoryCode;
	
	public String getThumbnailBase64() {
		 if (thumbnailBase64 != null) {
	            return thumbnailBase64.split(",")[1];
	     }
	     return null;
	}
	public void setThumbnailBase64(String thumbnailBase64) {
		this.thumbnailBase64 = thumbnailBase64;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

}
