package com.maven.namesilo7005.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DomainDto {
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

}
