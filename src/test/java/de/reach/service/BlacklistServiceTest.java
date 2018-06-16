package de.reach.service;

import de.reach.api.resources.BlackListResponse;
import de.reach.api.resources.BlackListModificationResponse;
import de.reach.persistent.model.IpEntity;
import de.reach.persistent.repo.IpRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BlacklistServiceTest {

    @InjectMocks
    BlackListService blackListService;

    @Mock
    IpRepository ipRepository;


    @Before
    public void setup() throws IOException{
    }

    @Test
    public void testQueryExistIp()  {
        IpEntity ipEntity = new IpEntity();
        ipEntity.setId(1l);
        ipEntity.setIpAddress("ip");

        when(ipRepository.getIpEntityByIpAddress(Matchers.anyString())).thenReturn(ipEntity);

        BlackListModificationResponse response = blackListService.checkIpIsExist("ip");
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getOperationStatus()).isEqualTo(Boolean.TRUE);
    }

    @Test
    public void testQueryNonExistIp()  {
        IpEntity ipEntity = new IpEntity();

        when(ipRepository.getIpEntityByIpAddress(Matchers.anyString())).thenReturn(ipEntity);

        BlackListModificationResponse response = blackListService.checkIpIsExist("ip");
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getOperationStatus()).isEqualTo(Boolean.FALSE);
    }

    @Test
    public void testAddExistIp()  {
        IpEntity ipEntity = new IpEntity();
        ipEntity.setId(1l);
        ipEntity.setIpAddress("ip");

        when(ipRepository.getIpEntityByIpAddress(Matchers.anyString())).thenReturn(ipEntity);

        BlackListModificationResponse response = blackListService.addIp("ip");
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getOperationStatus()).isEqualTo(Boolean.FALSE);
    }

    @Test
    public void testAddNonExistIp()  {
        IpEntity ipEntity = new IpEntity();

        when(ipRepository.getIpEntityByIpAddress(Matchers.anyString())).thenReturn(ipEntity);

        BlackListModificationResponse response = blackListService.addIp("ip");
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getOperationStatus()).isEqualTo(Boolean.TRUE);
    }

    @Test
    public void testDeleteExistIp()  {
        IpEntity ipEntity = new IpEntity();
        ipEntity.setId(1l);
        ipEntity.setIpAddress("ip");

        when(ipRepository.getIpEntityByIpAddress(Matchers.anyString())).thenReturn(ipEntity);

        BlackListModificationResponse response = blackListService.deleteIp("ip");
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getOperationStatus()).isEqualTo(Boolean.TRUE);
    }

    @Test
    public void testDeleteNonExistIp()  {
        IpEntity ipEntity = new IpEntity();

        when(ipRepository.getIpEntityByIpAddress(Matchers.anyString())).thenReturn(ipEntity);

        BlackListModificationResponse response = blackListService.deleteIp("ip");
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getOperationStatus()).isEqualTo(Boolean.FALSE);
    }


    @Test
    public void QueryBlackListWithExistIps()  {

        List<IpEntity> ipLists = new ArrayList<IpEntity>();
        IpEntity ipEntity1 = new IpEntity();
        ipEntity1.setId(1l);
        ipEntity1.setIpAddress("ip-1");
        ipLists.add(ipEntity1);

        IpEntity ipEntity2 = new IpEntity();
        ipEntity2.setId(2l);
        ipEntity2.setIpAddress("ip-2");
        ipLists.add(ipEntity2);

        when(ipRepository.findAll()).thenReturn(ipLists);

        BlackListResponse response = blackListService.getList();
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getBlackList()).isNotNull().size().isEqualTo(2);
    }

    @Test
    public void QueryBlackListWithEmptyList()  {

        when(ipRepository.findAll()).thenReturn(new ArrayList<IpEntity>());

        BlackListResponse response = blackListService.getList();
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getBlackList()).isNotNull().size().isEqualTo(0);
    }
}
