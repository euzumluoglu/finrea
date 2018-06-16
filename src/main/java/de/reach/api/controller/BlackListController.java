package de.reach.api.controller;

import de.reach.api.resources.BlackListResponse;
import de.reach.api.resources.BlacklistModificationResponse;
import de.reach.service.BlackListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/v1/ip/blacklist")
public class BlackListController {


    @Autowired
    private BlackListService blackListService;

    @RequestMapping(value = "/{ip}",method = RequestMethod.POST)
    public BlacklistModificationResponse addIp(@PathVariable("ip") String ip) {

        return blackListService.addIp(ip);
    }

    @RequestMapping(value = "/{ip}",method = RequestMethod.GET)
    public BlacklistModificationResponse getIp(@PathVariable("ip") String ip) {

        return blackListService.checkIpIsExist(ip);
    }

    @RequestMapping(value = "/{ip}",method = RequestMethod.DELETE)
    public BlacklistModificationResponse deleteIp(@PathVariable("ip") String ip) {

        return blackListService.deleteIp(ip);
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public BlackListResponse getBlackList() {

        return blackListService.getList();
    }


}
