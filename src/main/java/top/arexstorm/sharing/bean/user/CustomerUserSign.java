package top.arexstorm.sharing.bean.user;

import java.io.Serializable;
import java.math.BigDecimal;

public class CustomerUserSign extends UserSign implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Boolean signed;

    public CustomerUserSign() {
    }

    public CustomerUserSign(Integer days, BigDecimal points, Boolean signed) {
        super.setDays(days);
        super.setPoints(points);
        this.signed = signed;
    }

    public Boolean getSigned() {
        return signed;
    }

    public void setSigned(Boolean signed) {
        this.signed = signed;
    }
}
