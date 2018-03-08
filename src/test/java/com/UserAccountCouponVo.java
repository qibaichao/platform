package com;

/**
 * Created by jiazhiwen on 2016/11/24.
 */



import com.cyou.vo.Vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户中心优惠券VO
 */
public class UserAccountCouponVo extends Vo {


    /**
     * 优惠券Id
     */
    private String couponId = "";

    /**
     * 优惠券类型
     */
    private String couponType = "";

    /**
     * 优惠券类型 英文
     */
    private String couponTypeEng = "";

    /**
     * 生效日期
     */
    private Date validDateFrom;

    /**
     * 失效日期
     */
    private Date validDateEnd;

    /**
     * 优惠券面值
     */
    private BigDecimal couponValue;

    /**
     * 短期优惠券等奖励值
     */
    private BigDecimal couponRewardValue;

    /**
     * 原始优惠券面值，折扣券的面值会变成打折后的值
     */
    private BigDecimal originalCouponValue;

    /**
     * 允许使用的业务类型
     */
    private String allowBusinessCategory = "";

    /**
     * 允许使用的业务类型
     */
    private String allowBusinessCategoryShow = "";

    /**
     * 被使用的业务类型
     */
    private String usedBusinessCategory = "";

    /**
     * 投资金额
     */
    private BigDecimal investAmount;

    /**
     * 使用下限
     */
    private String minInvestAmount = "";

    /**
     * 使用上限
     */
    private String maxInvestAmount = "";

    /**
     * 消费备注
     */
    private String consumeMemo = "";


    /**
     * 使用时间
     */
    private Date consumeTime;

    /**
     * 名称
     */
    private String name = "";

    /**
     * 使用平台
     * MOBILE:移动端
     * PC:网站
     * 为空不限制
     */
    private String tradeMethod = "";


    /**
     * 过期提醒
     * 过期时间大于三天，不提醒
     * 过期时间等于三天，三天后过期
     * 过期时间等于两天，两天后过期
     * 过期时间等于一天，一天后过期
     */
    private String expireRemind = "";

    /**
     * 抵扣券抵扣率
     */
    private String discountRate = "";

    /**
     * 理财计划期数
     */
    private String businessId = "";

    /**
     * 加息利率
     */
    private String incrInterestRate = "";

    /**
     * 加息天数
     */
    private String incrInterestDays = "";

    /**
     * 续期允许的投资类型
     */
    private String incrRenewalBusinessCat = "";

    /**
     * 续期允许的投资类型
     */
    private String incrCurrentBusinessCat = "";

    /**
     * 营销文案标签
     */
    private String tag = "";

    /**
     * 红包状态
     */
    private String status = "";
    /**
     * 允许使用的业务对象名称 例如U计划发布前没有id,只能写名称
     */
    private String allowBusinessName = "";

    /**
     * 加息券可使用的U计划id, null时表示不限定
     */
    private String incrInterestCurrentBussinessId = "";

    /**
     * 当期 加息券续期允许的投资类型,可多选,null表示不限定,逗号分隔:U计划A,U计划B,U计划C,U新
     */
    private String incrInterestCurrentBusinessCategoryShow = "";

    /**
     * 当期 加息券续期允许的投资类型,可多选,null表示不限定,逗号分隔:U计划A,U计划B,U计划C,U新
     */
    private String incrInterestCurrentBusinessCategory = "";

    /**
     * 续期加息券续期允许的投资类型,可多选,null表示不限定,逗号分隔:U计划A,U计划B,U计划C,U新
     */
    private String incrInterestRenewalBusinessCategory = "";


    /**
     * U计划（正常计划/新手专享）投资期限
     */
    private String lockPeriod = "";
    /**
     * 续期U计划投资期限
     */
    private String lock_period_renewal = "";

    private String rangeValue = "";


    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public BigDecimal getCouponValue() {
        return couponValue;
    }

    public void setCouponValue(BigDecimal couponValue) {
        this.couponValue = couponValue;
    }

    public String getAllowBusinessCategory() {
        return allowBusinessCategory;
    }

    public void setAllowBusinessCategory(String allowBusinessCategory) {
        this.allowBusinessCategory = allowBusinessCategory;
    }

    public String getUsedBusinessCategory() {
        return usedBusinessCategory;
    }

    public void setUsedBusinessCategory(String usedBusinessCategory) {
        this.usedBusinessCategory = usedBusinessCategory;
    }

    public String getMinInvestAmount() {
        return minInvestAmount;
    }

    public void setMinInvestAmount(String minInvestAmount) {
        this.minInvestAmount = minInvestAmount;
    }

    public String getIncrInterestDays() {
        return incrInterestDays;
    }

    public void setIncrInterestDays(String incrInterestDays) {
        this.incrInterestDays = incrInterestDays;
    }

    public String getConsumeMemo() {
        return consumeMemo;
    }

    public void setConsumeMemo(String consumeMemo) {
        this.consumeMemo = consumeMemo;
    }

    public String getCouponTypeEng() {
        return couponTypeEng;
    }

    public void setCouponTypeEng(String couponTypeEng) {
        this.couponTypeEng = couponTypeEng;
    }

    public BigDecimal getCouponRewardValue() {
        return couponRewardValue;
    }

    public void setCouponRewardValue(BigDecimal couponRewardValue) {
        this.couponRewardValue = couponRewardValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getValidDateFrom() {
        return validDateFrom;
    }

    public void setValidDateFrom(Date validDateFrom) {
        this.validDateFrom = validDateFrom;
    }

    public Date getValidDateEnd() {
        return validDateEnd;
    }

    public void setValidDateEnd(Date validDateEnd) {
        this.validDateEnd = validDateEnd;
    }

    public Date getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(Date consumeTime) {
        this.consumeTime = consumeTime;
    }

    public String getTradeMethod() {
        return tradeMethod;
    }

    public void setTradeMethod(String tradeMethod) {
        this.tradeMethod = tradeMethod;
    }

    public String getExpireRemind() {
        return expireRemind;
    }

    public void setExpireRemind(String expireRemind) {
        this.expireRemind = expireRemind;
    }

    public String getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(String discountRate) {
        this.discountRate = discountRate;
    }

    public BigDecimal getOriginalCouponValue() {
        return originalCouponValue;
    }

    public void setOriginalCouponValue(BigDecimal originalCouponValue) {
        this.originalCouponValue = originalCouponValue;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getIncrInterestRate() {
        return incrInterestRate;
    }

    public void setIncrInterestRate(String incrInterestRate) {
        this.incrInterestRate = incrInterestRate;
    }

    public String getIncrRenewalBusinessCat() {
        return incrRenewalBusinessCat;
    }

    public void setIncrRenewalBusinessCat(String incrRenewalBusinessCat) {
        this.incrRenewalBusinessCat = incrRenewalBusinessCat;
    }

    public String getIncrCurrentBusinessCat() {
        return incrCurrentBusinessCat;
    }

    public void setIncrCurrentBusinessCat(String incrCurrentBusinessCat) {
        this.incrCurrentBusinessCat = incrCurrentBusinessCat;
    }

    public String getIncrInterestCurrentBussinessId() {
        return incrInterestCurrentBussinessId;
    }

    public void setIncrInterestCurrentBussinessId(String incrInterestCurrentBussinessId) {
        this.incrInterestCurrentBussinessId = incrInterestCurrentBussinessId;
    }

    public String getIncrInterestCurrentBusinessCategory() {
        return incrInterestCurrentBusinessCategory;
    }

    public void setIncrInterestCurrentBusinessCategory(String incrInterestCurrentBusinessCategory) {
        this.incrInterestCurrentBusinessCategory = incrInterestCurrentBusinessCategory;
    }

    public String getIncrInterestRenewalBusinessCategory() {
        return incrInterestRenewalBusinessCategory;
    }

    public void setIncrInterestRenewalBusinessCategory(String incrInterestRenewalBusinessCategory) {
        this.incrInterestRenewalBusinessCategory = incrInterestRenewalBusinessCategory;
    }

    public String getMaxInvestAmount() {
        return maxInvestAmount;
    }

    public void setMaxInvestAmount(String maxInvestAmount) {
        this.maxInvestAmount = maxInvestAmount;
    }

    public String getAllowBusinessName() {
        return allowBusinessName;
    }

    public void setAllowBusinessName(String allowBusinessName) {
        this.allowBusinessName = allowBusinessName;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLockPeriod() {
        return lockPeriod;
    }

    public void setLockPeriod(String lockPeriod) {
        this.lockPeriod = lockPeriod;
    }

    public String getLock_period_renewal() {
        return lock_period_renewal;
    }

    public void setLock_period_renewal(String lock_period_renewal) {
        this.lock_period_renewal = lock_period_renewal;
    }

    public BigDecimal getInvestAmount() {
        return investAmount;
    }

    public void setInvestAmount(BigDecimal investAmount) {
        this.investAmount = investAmount;
    }

    public String getAllowBusinessCategoryShow() {
        return allowBusinessCategoryShow;
    }

    public void setAllowBusinessCategoryShow(String allowBusinessCategoryShow) {
        this.allowBusinessCategoryShow = allowBusinessCategoryShow;
    }

    public String getIncrInterestCurrentBusinessCategoryShow() {
        return incrInterestCurrentBusinessCategoryShow;
    }

    public void setIncrInterestCurrentBusinessCategoryShow(String incrInterestCurrentBusinessCategoryShow) {
        this.incrInterestCurrentBusinessCategoryShow = incrInterestCurrentBusinessCategoryShow;
    }

    public String getRangeValue() {
        return rangeValue;
    }

    public void setRangeValue(String rangeValue) {

        this.rangeValue = rangeValue;
    }

}
