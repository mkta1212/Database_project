
public class activity {
	private String activity_ID;
	private String title;
	private String location;
	private String content;
	
	public activity(String id,String title,String location,String content) {
		this.activity_ID=id;
		this.title=title;
		this.location=location;
		this.content=content;
	}

	public String getActivity_ID() {
		return activity_ID;
	}

	public void setActivity_ID(String activity_ID) {
		this.activity_ID = activity_ID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
