package cn.bjfu.im;

public class OrderDetailVO {
	private int oid;
	private int gid;
	private int amount;

	OrderDetailVO(int t_oid, int t_gid, int t_amount) {
		this.oid = t_oid;
		this.gid = t_gid;
		this.amount = t_amount;
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "OrderDetailVO [oid=" + oid + ", gid=" + gid + ", amount="
				+ amount + "]";
	}

}
