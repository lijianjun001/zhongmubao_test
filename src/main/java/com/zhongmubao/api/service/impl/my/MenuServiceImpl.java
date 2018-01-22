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

import org.json.JSONObject;
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


        String dateFormat ="yyyy-MM-dd HH:mm:ss";
        String dateStr = "2017-12-01 00:00:00";
        CustomerSina customerSina = customerSinaDao.getCustomerSinaById(customer.getId());
        CustomerHF customerHF = customerHFDao.getCustomerHFById(customer.getId());
        if (customer.getCreated().getTime() < (new SimpleDateFormat(dateFormat).parse(dateStr)).getTime()) {
            if(customerSina!=null) {
                ListItemModel listItemModelWallet = new ListItemModel();
                listItemModelWallet.setIcon("personal-qianbao.png");
                listItemModelWallet.setTitle("我的钱包");
                listItemModelWallet.setUrl("/Emubao/WebApp#/Wallet");
                listItemModelWallet.setAction("wallet");
                listItemModelWallet.setJumpType("00");
                listItemModels.add(listItemModelWallet);
            }
        }
        if(customerHF!=null) {
         if(customerHF.getIsBandCard()&&customerHF.getIsBosAcct()&&!StringUtil.isNullOrEmpty(customerHF.getUsrCustId())){
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
         }
        }


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

/*
ListItemModel listItemModelHfAuth = new ListItemModel();
listItemModelHfAuth.setIcon("personal-help.png");
listItemModelHfAuth.setTitle("汇付开户");
listItemModelHfAuth.setUrl("/Customer/HfAuth");
listItemModelHfAuth.setAction("hfauth");
listItemModelHfAuth.setJumpType("00");
listItemModels.add(listItemModelHfAuth);
*/

        for (ListItemModel itemModel :
                listItemModels) {
            itemModel.setUrl(url + itemModel.getUrl());
            itemModel.setIcon(Constants.RESOURES_ADDRESS_IMAGES + itemModel.getIcon());
        }

        listViewModel.setList(listItemModels);
        return listViewModel;
    }

}
