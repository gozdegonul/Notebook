package package1;

public class Note {
	
	int noteId;
	String noteTitle , noteExp;

	
	public Note(int noteId, String noteTitle, String noteExp) {
		super();
		this.noteId = noteId;
		this.noteTitle = noteTitle;
		this.noteExp = noteExp;
	}
	public int getNoteId() {
		return noteId;
	}
	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}
	public String getNoteTitle() {
		return noteTitle;
	}
	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}
	public String getNoteExp() {
		return noteExp;
	}
	public void setNoteExp(String noteExp) {
		this.noteExp = noteExp;
	}
	
	

}
