package de.reach.service;

import de.reach.api.resources.BlackListResponse;
import de.reach.api.resources.BlacklistModificationResponse;
import de.reach.persistent.model.IpEntity;
import de.reach.persistent.repo.IpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlackListService {

    @Autowired
    private IpRepository ipRepository;

    public BlacklistModificationResponse checkIpIsExist(String ip){

        BlacklistModificationResponse response = new BlacklistModificationResponse();
        response.setIp(ip);
        IpEntity ipEntity = ipRepository.getIpEntityByIpAddress(ip);

        if(ipEntity!=null && ipEntity.getId()!=null){
            response.setOperationStatus(Boolean.TRUE);
        } else {
            response.setOperationStatus(Boolean.FALSE);
            response.setMessage("IP is not in blackList");
        }
        return response;
    }

    public BlacklistModificationResponse addIp(String ip){

        BlacklistModificationResponse response = new BlacklistModificationResponse();
        response.setIp(ip);
        IpEntity ipEntity = ipRepository.getIpEntityByIpAddress(ip);
        if(ipEntity==null || ipEntity.getId()==null) {
            ipEntity = new IpEntity();
            ipEntity.setIpAddress(ip);
            ipRepository.save(ipEntity);
            response.setOperationStatus(Boolean.TRUE);
        } else {
            response.setOperationStatus(Boolean.FALSE);
            response.setMessage("IP is already in blackList");
        }
        return response;
    }

    public BlacklistModificationResponse deleteIp(String ip){

        BlacklistModificationResponse response = new BlacklistModificationResponse();
        response.setIp(ip);
        IpEntity ipEntity = ipRepository.getIpEntityByIpAddress(ip);
        if(ipEntity!=null && ipEntity.getId()!=null){
            response.setOperationStatus(Boolean.TRUE);
            ipRepository.delete(ipEntity);
        } else {
            response.setOperationStatus(Boolean.FALSE);
            response.setMessage("IP is not in blackList");
        }
        return response;
    }

    public BlackListResponse getList(){

        List<IpEntity> ipEntities =  ipRepository.findAll();
        List<String> ipList = new ArrayList<String>(ipEntities.size());
        for(IpEntity ipEntity:ipEntities){
            ipList.add(ipEntity.getIpAddress());
        }
        BlackListResponse response = new BlackListResponse();
        response.setBlackList(ipList);
        return response;
    }
}
