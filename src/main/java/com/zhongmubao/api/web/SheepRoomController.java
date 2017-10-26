package com.zhongmubao.api.web;

import com.zhongmubao.api.authorization.annotation.*;
import com.zhongmubao.api.dto.request.sheep.room.*;
import com.zhongmubao.api.dto.response.ReponseModel;
import com.zhongmubao.api.dto.response.sheep.room.*;
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
    public ResponseEntity<ReponseModel> room(@CurrentUser Customer customer, HttpEntity<SheepRoomRequestModel> model) {
        try {
            SheepRoomViewModel sheepRoomViewModel = sheepRoomService.room(customer, model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(sheepRoomViewModel), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/room/orders", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> orders(@CurrentUser Customer customer, HttpEntity<SheepRoomOrdersRequestModel> model) {
        try {
            SheepRoomOrdersViewModel sheepRoomOrdersViewModel = sheepRoomService.orders(customer, model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(sheepRoomOrdersViewModel), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/room/breedprogress", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ReponseModel> breedProgress(HttpEntity<SheepRoomBreedProgressRequestModel> model) {
        try {
            SheepRoomBreedProgressViewModel sheepRoomBreedProgressViewModel = sheepRoomService.breedProgress(model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(sheepRoomBreedProgressViewModel), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/room/insurance", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ReponseModel> insurance(HttpEntity<SheepRoomInsuranceRequestModel> model) {
        try {
            SheepRoomInsuranceViewModel sheepRoomInsuranceViewModel = sheepRoomService.insurance(model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(sheepRoomInsuranceViewModel), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/room/eartag", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> eartag(@CurrentUser Customer customer, HttpEntity<SheepRoomEarTagRequestModel> model) {
        try {
            SheepRoomEarTagViewModel sheepRoomEarTagViewModel = sheepRoomService.eartag(customer, model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(sheepRoomEarTagViewModel), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

}
