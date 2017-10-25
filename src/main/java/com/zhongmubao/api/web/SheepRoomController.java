package com.zhongmubao.api.web;

import com.zhongmubao.api.authorization.annotation.*;
import com.zhongmubao.api.dto.request.sheep.room.SheepRoomRequestModel;
import com.zhongmubao.api.dto.response.ReponseModel;
import com.zhongmubao.api.dto.response.sheep.room.SheepRoomViewModel;
import com.zhongmubao.api.entity.Customer;
import com.zhongmubao.api.exception.ApiException;
import com.zhongmubao.api.service.SheepRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 我的羊圈
 *
 * @author xy
 */
@RestController
@RequestMapping("/sheep")
public class SheepRoomController {

    private final SheepRoomService sheepRoomService;

    @Autowired
    public SheepRoomController(SheepRoomService sheepRoomService) {
        this.sheepRoomService = sheepRoomService;
    }

    @RequestMapping(value = "/room", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> touTiaoAdv(@CurrentUser Customer customer, HttpEntity<SheepRoomRequestModel> model) {
        try {
            SheepRoomViewModel sheepRoomViewModel = sheepRoomService.sheepRoom(customer, model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(sheepRoomViewModel), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

}
