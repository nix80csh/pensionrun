import com.pensionrun.util.DateUtil;

public class DateUtilTestMainApplication {
	public static void main(String[] args) throws Exception{
		System.out.println(DateUtil.getDiffDayList("1986-10-04", "1986-10-10", "yyyy-MM-dd").toString());
	}
}
