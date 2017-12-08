package com.zhongmubao.api.service.impl.my;
import com.zhongmubao.api.config.Constants;
import com.zhongmubao.api.config.ResultStatus;
import com.zhongmubao.api.config.enmu.Domain;
import com.zhongmubao.api.config.enmu.RealNameStatus;
import com.zhongmubao.api.dao.CustomerHFDao;
import com.zhongmubao.api.dao.CustomerSinaDao;
import com.zhongmubao.api.dto.request.my.menu.ListRequestModel;
import com.zhongmubao.api.dto.request.my.RealNameRequestModel;
import com.zhongmubao.api.dto.response.my.menu.ListViewModel;
import com.zhongmubao.api.dto.response.my.RealNameViewModel;
import com.zhongmubao.api.dto.response.my.menu.ListItemModel;
import com.zhongmubao.api.entity.Customer;
import com.zhongmubao.api.entity.CustomerHF;
import com.zhongmubao.api.entity.CustomerSina;
import com.zhongmubao.api.exception.ApiException;
import com.zhongmubao.api.service.impl.BaseService;
import com.zhongmubao.api.service.my.MenuService;
import com.zhongmubao.api.util.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 个人中心
 *
 * @author xy
 */
@Service
public class MenuServiceImpl extends BaseService implements MenuService {
    private final CustomerHFDao customerHFDao;
    private final CustomerSinaDao customerSinaDao;

    @Autowired
    public MenuServiceImpl(CustomerHFDao customerHFDao, CustomerSinaDao customerSinaDao) {
        this.customerHFDao = customerHFDao;
        this.customerSinaDao = customerSinaDao;
    }

    @Override
    public ListViewModel list(Customer customer, ListRequestModel model) throws Exception {
        String url = "";
        if (model == null) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        if (model.getPlatform() != null) {
            if (model.getPlatform().equals(Domain.IOS.getPlatform())) {
                url = Domain.IOS.getDomain();
            }
            if (model.getPlatform().equals(Domain.ANDROID.getPlatform())) {
                url = Domain.ANDROID.getDomain();
            }
        }
        ListViewModel listViewModel = new ListViewModel();
        List<ListItemModel> listItemModels = new ArrayList<>();

        ListItemModel listItemModelRemindIndex = new ListItemModel();
        listItemModelRemindIndex.setIcon("personal-remind.png");
        listItemModelRemindIndex.setTitle("购羊提醒");
        listItemModelRemindIndex.setUrl("/Sheep/RemindIndex");
        listItemModelRemindIndex.setAction("remindindex");
        listItemModelRemindIndex.setJumpType("00");
        listItemModels.add(listItemModelRemindIndex);

        ListItemModel listItemModelWallet = new ListItemModel();
        listItemModelWallet.setIcon("personal-qianbao.png");
        listItemModelWallet.setTitle("我的钱包");
        listItemModelWallet.setUrl("/Emubao/WebApp#/Wallet");
        listItemModelWallet.setAction("wallet");
        listItemModelWallet.setJumpType("00");
        listItemModels.add(listItemModelWallet);

        ListItemModel listItemModelHfWallet = new ListItemModel();
        listItemModelHfWallet.setIcon("personal-qianbao.png");
        listItemModelHfWallet.setTitle("汇付钱包");
        listItemModelHfWallet.setUrl("/Customer/HfWallet");
        listItemModelHfWallet.setAction("hfwallet");
        listItemModelHfWallet.setJumpType("00");
        listItemModels.add(listItemModelHfWallet);

        ListItemModel listItemModelHfCard = new ListItemModel();
        listItemModelHfCard.setIcon("personal-yinhangka.png");
        listItemModelHfCard.setTitle("汇付银行卡");
        listItemModelHfCard.setUrl("/Customer/HfCard");
        listItemModelHfCard.setAction("hfcard");
        listItemModelHfCard.setJumpType("00");
        listItemModels.add(listItemModelHfCard);

        ListItemModel listItemModelBonusList = new ListItemModel();
        listItemModelBonusList.setIcon("personal-xianjin.png");
        listItemModelBonusList.setTitle("现金红包");
        listItemModelBonusList.setUrl("/Customer/BonusList");
        listItemModelBonusList.setAction("bonuslist");
        listItemModelBonusList.setJumpType("00");
        listItemModels.add(listItemModelBonusList);

        ListItemModel listItemModelOrderList = new ListItemModel();
        listItemModelOrderList.setIcon("personal-dingdan.png");
        listItemModelOrderList.setTitle("我的订单");
        listItemModelOrderList.setUrl("/Emubao/WebApp#/OrderList");
        listItemModelOrderList.setAction("orderlist");
        listItemModelOrderList.setJumpType("00");
        listItemModels.add(listItemModelOrderList);

        ListItemModel listItemModelAddressList = new ListItemModel();
        listItemModelAddressList.setIcon("personal-address.png");
        listItemModelAddressList.setTitle("收货地址");
        listItemModelAddressList.setUrl("/Emubao/WebApp#/AddressList");
        listItemModelAddressList.setAction("addresslist");
        listItemModelAddressList.setJumpType("00");
        listItemModels.add(listItemModelAddressList);

        ListItemModel listItemModelInvite = new ListItemModel();
        listItemModelInvite.setIcon("personal-referee.png");
        listItemModelInvite.setTitle("我的推荐人");
        listItemModelInvite.setUrl("/Emubao/WebApp#/Invite");
        listItemModelInvite.setAction("invite");
        listItemModelInvite.setJumpType("01");
        listItemModels.add(listItemModelInvite);

        ListItemModel listItemModelSettings = new ListItemModel();
        listItemModelSettings.setIcon("personal-set.png");
        listItemModelSettings.setTitle("设置");
        listItemModelSettings.setUrl("/Emubao/WebApp#/Settings");
        listItemModelSettings.setAction("settings");
        listItemModelSettings.setJumpType("01");
        listItemModels.add(listItemModelSettings);

        for (ListItemModel itemModel :
                listItemModels) {
            itemModel.setUrl(url + itemModel.getUrl());
            itemModel.setIcon(Constants.RESOURES_ADDRESS_IMAGES + itemModel.getIcon());
        }

        listViewModel.setList(listItemModels);
        return listViewModel;
    }

    @Override
    public RealNameViewModel choosePaymentRealName(Customer customer, RealNameRequestModel model) throws Exception {

        RealNameViewModel realNameViewModel = new RealNameViewModel();
        CustomerHF customerHF = customerHFDao.getCustomerHFById(customer.getId());

        //Redis 获取显示新浪 还是 汇付
        boolean ishf = false;
        String dateFormat ="yyyy-MM-dd HH:mm:ss";
        String dateStr = "2017-12-01 00:00:00";
        if (customer.getCreated().getTime() > (new SimpleDateFormat(dateFormat).parse(dateStr)).getTime()) {
            ishf = true;
        }
        if (!ishf && customerHF != null) {
            ishf = true;
        }

        //显示汇付 or 新浪
        if (ishf) {
            if (customerHF == null) {
                realNameViewModel.setRealName(RealNameStatus.HFF.getName());
                realNameViewModel.setRealNameSatus(RealNameStatus.HFF.getStatus());
                realNameViewModel.setRealNameType(RealNameStatus.HFF.getType());
                realNameViewModel.setRealNameImg(Constants.RESOURES_ADDRESS_IMAGES + RealNameStatus.HFF.getImg());
                return realNameViewModel;
            }
            if (!StringUtil.isNullOrEmpty(customerHF.getUsrCustId()) && customerHF.getIsBandCard() && customerHF.getIsBosAcct()) {
                realNameViewModel.setRealName(RealNameStatus.HFS.getName());
                realNameViewModel.setRealNameSatus(RealNameStatus.HFS.getStatus());
                realNameViewModel.setRealNameType(RealNameStatus.HFS.getType());
                realNameViewModel.setRealNameImg(Constants.RESOURES_ADDRESS_IMAGES + RealNameStatus.HFS.getImg());
            } else if (!StringUtil.isNullOrEmpty(customerHF.getUsrCustId()) && !customerHF.getIsBosAcct()) {
                realNameViewModel.setRealName(RealNameStatus.HFB.getName());
                realNameViewModel.setRealNameSatus(RealNameStatus.HFB.getStatus());
                realNameViewModel.setRealNameType(RealNameStatus.HFB.getType());
                realNameViewModel.setRealNameImg(Constants.RESOURES_ADDRESS_IMAGES + RealNameStatus.HFB.getImg());
            } else {
                realNameViewModel.setRealName(RealNameStatus.HFF.getName());
                realNameViewModel.setRealNameSatus(RealNameStatus.HFF.getStatus());
                realNameViewModel.setRealNameType(RealNameStatus.HFF.getType());
                realNameViewModel.setRealNameImg(Constants.RESOURES_ADDRESS_IMAGES + RealNameStatus.HFF.getImg());
            }
        } else {
            CustomerSina customerSina = customerSinaDao.getCustomerSinaById(customer.getId());
            if (customerSina == null) {
                realNameViewModel.setRealName(RealNameStatus.XLF.getName());
                realNameViewModel.setRealNameSatus(RealNameStatus.XLF.getStatus());
                realNameViewModel.setRealNameType(RealNameStatus.XLF.getType());
                realNameViewModel.setRealNameImg(Constants.RESOURES_ADDRESS_IMAGES + RealNameStatus.XLF.getImg());
                return realNameViewModel;
            }

            if (customerSina.getIsRealName()) {
                realNameViewModel.setRealName(RealNameStatus.XLS.getName());
                realNameViewModel.setRealNameSatus(RealNameStatus.XLS.getStatus());
                realNameViewModel.setRealNameType(RealNameStatus.XLS.getType());
                realNameViewModel.setRealNameImg(Constants.RESOURES_ADDRESS_IMAGES + RealNameStatus.XLS.getImg());
            } else {
                realNameViewModel.setRealName(RealNameStatus.XLF.getName());
                realNameViewModel.setRealNameSatus(RealNameStatus.XLF.getStatus());
                realNameViewModel.setRealNameType(RealNameStatus.XLF.getType());
                realNameViewModel.setRealNameImg(Constants.RESOURES_ADDRESS_IMAGES + RealNameStatus.XLF.getImg());
            }

        }
        return realNameViewModel;
    }
}
