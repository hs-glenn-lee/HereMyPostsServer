package web.model.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import web.model.jpa.entities.Tag;

public class MyTagUtils {
	
	private static final String TAG_SEPERATOR = "\u001F";

	private List<String> myTagList;

	public MyTagUtils(String myTags) {
		myTagList = new ArrayList<String>();
		String[] myTagsArr = myTags.split(TAG_SEPERATOR);
		for(String s : myTagsArr) {
			myTagList.add(s);
		}
	}
	
	public void addTag(String tagName) {//append tag if there's no same tag in tagsString 
		if(getIndexOfMyTagList(tagName) == null) {
			this.myTagList.add(tagName);
		}
	}
	
	public void addTags(List<Tag> tags) {
		for(Tag tag : tags) {
			this.addTag(tag.getName());
		}
	}
	
	public void removeTag(String tagName) {//remove tag
		Integer targetIdx = getIndexOfMyTagList(tagName);
		if(targetIdx != null) {
			myTagList.remove(targetIdx.intValue());
		}
	}
	
	private Integer getIndexOfMyTagList(String tagName) {//if tagsString contain input tagName return true, else  false;
		for(int i = 0; i < myTagList.size(); i++) {
			String storedTagName = myTagList.get(i);
			if(tagName.equals(storedTagName)) {
				return i;
			}
		}
		return null;
	}
	
	public String getTagsString () {
		return String.join(TAG_SEPERATOR, myTagList);
	}
	
	public List<String> getMyTagList() {
		return myTagList;
	}
	
}
