package util;

import org.hibernate.Transaction;

public class CloseUtil {
	public void close(Transaction ts) {
		if(ts != null) {
			ts = null;
			System.out.println("事务已经被销毁。");
		}
	}
}
