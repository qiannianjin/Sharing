package top.arexstorm.sharing.bean.order;

import java.io.Serializable;
import java.util.Date;

public class OrderDetail implements Serializable {
    private Long id;

    private String orderdetailid;

    private String orderid;

    private String informationid;

    private String buyerid;

    private String sellerid;

    private Integer count;

    private Short status;

    private Date createtime;

    private Date lasttime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderdetailid() {
        return orderdetailid;
    }

    public void setOrderdetailid(String orderdetailid) {
        this.orderdetailid = orderdetailid == null ? null : orderdetailid.trim();
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public String getInformationid() {
        return informationid;
    }

    public void setInformationid(String informationid) {
        this.informationid = informationid == null ? null : informationid.trim();
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderdetailid=").append(orderdetailid);
        sb.append(", orderid=").append(orderid);
        sb.append(", informationid=").append(informationid);
        sb.append(", buyerid=").append(buyerid);
        sb.append(", sellerid=").append(sellerid);
        sb.append(", count=").append(count);
        sb.append(", status=").append(status);
        sb.append(", createtime=").append(createtime);
        sb.append(", lasttime=").append(lasttime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}