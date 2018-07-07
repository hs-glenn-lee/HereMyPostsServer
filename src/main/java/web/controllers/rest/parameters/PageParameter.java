package web.controllers.rest.parameters;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class PageParameter {
	
	public final static String DESC = "desc";
	public final static String ASC = "asc";
	
	private Integer size;
	private Integer page;
	private String direction;
	private String sortProperty;
	
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}

	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	
	public String getSortProperty() {
		return sortProperty;
	}
	public void setSortProperty(String sortProperty) {
		this.sortProperty = sortProperty;
	}
	
	public PageRequest toPageRequest() {
		validateBeforeToPageRequest();
		Direction dirc = ("asc".equals(this.direction))? Direction.ASC : Direction.DESC;
		PageRequest pReq = new PageRequest(page.intValue(), size.intValue(), new Sort(dirc, sortProperty));
		return pReq;
	}
	/*
	new Sort(Sort.Direction.DESC, "description")
    .and(new Sort(Sort.Direction.ASC, "title"));*/
	
	public void validateBeforeToPageRequest() {
		if(page == null) { throw new IllegalStateException("PageParameter.page is null.");	}
		if(size == null) { throw new IllegalStateException("PageParameter.size is null.");	}
		if(sortProperty == null) { throw new IllegalStateException("PageParameter.sortProperties is null.");	}
		if(direction == null) { throw new IllegalStateException("PageParameter.direction is null.");	}
		if( !(DESC.equals(this.direction) || ASC.equals(this.direction)) ) { throw new IllegalStateException("PageParameter.direction is not designated value.");}
	}
}
