package OldBookManager;

public class OldBookManager {
	static private OldBookManager obManager;
	private int articleNum;

	private OldBookManager(){
		articleNum = 1;
	}
	
	static public OldBookManager getInstance(){
		if(obManager == null){
			obManager = new OldBookManager();
			return obManager;
		}
		else
			return obManager;
	}
	
	public int getArticleNum() {
		return articleNum;
	}
	
	public void setArticleNum(int articleNum) {
		this.articleNum = articleNum;
	}

}
