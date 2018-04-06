package top.arexstorm.sharing.bean.order;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private Long id;

    private String orderid;

    private Integer count;

    private Short back;

    private Date createtime;

    private Date lasttime;

    private Short status;

    private String buyerid;

    private String sellerid;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Short getBack() {
        return back;
    }

    public void setBack(Short back) {
        this.back = back;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getLasttime() {
        return lasttime;
    }

    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getBuyerid() {
        return buyerid;
    }

    public void setBuyerid(String buyerid) {
        this.buyerid = buyerid == null ? null : buyerid.trim();
    }

    public String getSellerid() {
        return sellerid;
    }

    public void setSellerid(String sellerid) {
        this.sellerid = sellerid == null ? null : sellerid.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderid=").append(orderid);
        sb.append(", count=").append(count);
        sb.append(", back=").append(back);
        sb.append(", createtime=").append(createtime);
        sb.append(", lasttime=").append(lasttime);
        sb.append(", status=").append(status);
        sb.append(", buyerid=").append(buyerid);
        sb.append(", sellerid=").append(sellerid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}