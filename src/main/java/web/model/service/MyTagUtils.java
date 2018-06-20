package web.model.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyTagUtils {
	
	private static final String TAG_SEPERATOR = "\u001F";
	
	private String myTags;
	
	private List<String> myTagList = new ArrayList<String>();

	public MyTagUtils(String myTags) {
		this.myTags = myTags;
		String[] myTagsArr = myTags.split(TAG_SEPERATOR);
		this.myTagList = Arrays.asList(myTagsArr);
	}
	
	public void addTag(String tagName) {//append tag if there's no same tag in tagsString 
		if(getIndexOfMyTagList(tagName) != null) {
			myTagList.add(tagName);
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
			if(storedTagName.equals(tagName)) {
				return i;
			}
		}
		return null;
	}
	
	public String getTagsString () {
		return myTags;
	}
	
	
}
