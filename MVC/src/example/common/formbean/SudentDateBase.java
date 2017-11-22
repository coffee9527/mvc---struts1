package example.common.formbean;

import java.util.ArrayList;
import java.util.List;
/**
 * 模拟数据库的学生表来存储数据
 * @author Administrator
 *
 */
public class SudentDateBase {
	private static List<Student> studentList = new ArrayList<Student>();
	
	private SudentDateBase(){}
	
	public static void save(Student student) {
		studentList.add(student);
	}
	
	public static int saveAll(List<Student> students) {
		if(studentList.addAll(students)) {
			return students.size();
		}
		return 0;
	}
	
	public static Student select(int index) {
		return studentList.get(index);
	}
	
	public static List<Student> selectAll() {
		return studentList;
	}
	
	public static void delete(int index) {
		studentList.remove(index);
	}
	
	public static void delete(Student student) {
		studentList.remove(student);
	}
}
