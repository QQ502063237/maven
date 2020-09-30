package com.maven.common.entity.namesilo7005;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbDomain {
    /*主键*/
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    /*域名*/
    private String domain;
    /*是否可用 能否add*/
    private String available;
    /*额外费用*/
    private String premium;
    /*现在价格  支付金额*/
    private BigDecimal currentPrice;
    /*定期价格*/
    private BigDecimal regularPrice;
    /*更新价格*/
    private BigDecimal renewalPrice;
    /*是否已经添加到购物车*/
    private String addedToCart;
    /*拍卖类型*/
    private String auctionType;
    /*拍卖地址*/
    private String auctionUrl;
    /*最低展现价格*/
    private BigDecimal minimumBid;
    /*更新时间*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date upTime=new Date();

}
