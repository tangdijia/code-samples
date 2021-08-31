package cn.com.tdj.sc.obj;

import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 */
@Data
public class PayOrder implements java.io.Serializable {

    private Integer orderId;

    private String cardNo;

    private Long amount;

    private Date time;

    public PayOrder(Integer orderId, String cardNo, Long amount, String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.orderId = orderId;
        this.cardNo = cardNo;
        this.amount = amount;
        try {
            this.time = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
