package fresh.util;

import fresh.util.BaseException;

public class DbException extends BaseException {
	public DbException(java.lang.Throwable ex){
		super("Êý¾Ý¿â²Ù×÷´íÎó£º"+ex.getMessage());
	}
}
