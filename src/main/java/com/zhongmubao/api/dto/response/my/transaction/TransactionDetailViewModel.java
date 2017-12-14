package com.zhongmubao.api.dto.response.my.transaction;


/**
 * 交易详情记录视图实体
 *
 * @author 米立林
 */
public class TransactionDetailViewModel {
    public TransactionDetailViewModel() {
    }

    public TransactionDetailViewModel(String billType, WithdrawDetailModel withdrawDetailModel, BuySheepDetailModel buySheepDetailModel, RechargeDetailModel rechargeDetailModel) {
        this.billType = billType;
        this.withdrawDetailModel = withdrawDetailModel;
        this.buySheepDetailModel = buySheepDetailModel;
        this.rechargeDetailModel = rechargeDetailModel;
    }

    /**
     * 账单类型（充值、提现、买羊、赎回）
     */
    private String billType;

    /**
     * 交易详情json
     */
    private String transactionDetail;

    /**
     * 账户余额
     */
    private String balance;

    /**
     * 充值详情
     */
    private RechargeDetailModel rechargeDetailModel;

    /**
     * 提现详情
     */
    private WithdrawDetailModel withdrawDetailModel;

    /**
     * 买羊详情
     */
    private BuySheepDetailModel buySheepDetailModel;

    /**
     * 赎回详情
     */
    private RedeemDetailModel redeemDetailModel;

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public WithdrawDetailModel getWithdrawDetailModel() {
        return withdrawDetailModel;
    }

    public void setWithdrawDetailModel(WithdrawDetailModel withdrawDetailModel) {
        this.withdrawDetailModel = withdrawDetailModel;
    }

    public BuySheepDetailModel getBuySheepDetailModel() {
        return buySheepDetailModel;
    }

    public void setBuySheepDetailModel(BuySheepDetailModel buySheepDetailModel) {
        this.buySheepDetailModel = buySheepDetailModel;
    }

    public RechargeDetailModel getRechargeDetailModel() {
        return rechargeDetailModel;
    }

    public void setRechargeDetailModel(RechargeDetailModel rechargeDetailModel) {
        this.rechargeDetailModel = rechargeDetailModel;
    }

    public RedeemDetailModel getRedeemDetailModel() {
        return redeemDetailModel;
    }

    public void setRedeemDetailModel(RedeemDetailModel redeemDetailModel) {
        this.redeemDetailModel = redeemDetailModel;
    }

    public String getTransactionDetail() {
        return transactionDetail;
    }

    public void setTransactionDetail(String transactionDetail) {
        this.transactionDetail = transactionDetail;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
