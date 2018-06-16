package de.reach.api.controller;

import de.reach.BlacklistApplication;
import de.reach.api.resources.BlackListResponse;
import de.reach.api.resources.BlacklistModificationResponse;
import de.reach.persistent.model.IpEntity;
import de.reach.persistent.repo.IpRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.MultiValueMap;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BlacklistApplication.class},webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BlackListControllerTest {


    @Autowired
    private IpRepository ipRepository;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @After
    public void tearDown(){
        ipRepository.deleteAll();
    }


    @Test
    public void queryExistIp() throws IOException {
        saveIp("11");

        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity entity = new HttpEntity<>(null,headers);

        ResponseEntity<BlacklistModificationResponse> responseEntity = testRestTemplate.exchange("/v1/blacklist/11", HttpMethod.GET,entity,BlacklistModificationResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getOperationStatus()).isEqualTo(Boolean.TRUE);
    }

    @Test
    public void queryNonExistIp() throws IOException {
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity entity = new HttpEntity<>(null,headers);
        ResponseEntity<BlacklistModificationResponse> responseEntity = testRestTemplate.exchange("/v1/blacklist/11", HttpMethod.GET,entity,BlacklistModificationResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getOperationStatus()).isEqualTo(Boolean.FALSE);
    }

    @Test
    public void addNewIp() throws IOException {
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity entity = new HttpEntity<>(null,headers);

        ResponseEntity<BlacklistModificationResponse> responseEntity = testRestTemplate.exchange("/v1/blacklist/11", HttpMethod.POST,entity,BlacklistModificationResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getOperationStatus()).isEqualTo(Boolean.TRUE);
    }

    @Test
    public void reAddExistIp() throws IOException {
        saveIp("11");
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity entity = new HttpEntity<>(null,headers);

        ResponseEntity<BlacklistModificationResponse> responseEntity = testRestTemplate.exchange("/v1/blacklist/11", HttpMethod.POST,entity,BlacklistModificationResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getOperationStatus()).isEqualTo(Boolean.FALSE);
    }

    @Test
    public void deleteExistIp() throws IOException {
        saveIp("11");

        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity entity = new HttpEntity<>(null,headers);

        ResponseEntity<BlacklistModificationResponse> responseEntity = testRestTemplate.exchange("/v1/blacklist/11", HttpMethod.DELETE,entity,BlacklistModificationResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getOperationStatus()).isEqualTo(Boolean.TRUE);
    }

    @Test
    public void deleteNonExistIp() throws IOException {
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity entity = new HttpEntity<>(null,headers);

        ResponseEntity<BlacklistModificationResponse> responseEntity = testRestTemplate.exchange("/v1/blacklist/11", HttpMethod.DELETE,entity,BlacklistModificationResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getOperationStatus()).isEqualTo(Boolean.FALSE);
    }

    @Test
    public void queryListWithIps() throws IOException {
        saveIp("11");
        saveIp("12");
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity entity = new HttpEntity<>(null,headers);

        ResponseEntity<BlackListResponse> responseEntity = testRestTemplate.exchange("/v1/blacklist", HttpMethod.GET,entity,BlackListResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getBlackList()).isNotNull().size().isEqualTo(2);
    }

    @Test
    public void queryEmptyList() throws IOException {
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity entity = new HttpEntity<>(null,headers);

        ResponseEntity<BlackListResponse> responseEntity = testRestTemplate.exchange("/v1/blacklist", HttpMethod.GET,entity,BlackListResponse.class);


        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getBlackList()).isEmpty();
    }

    private void saveIp(String ip){
        IpEntity ipEntity = new IpEntity();
        ipEntity.setIpAddress(ip);
        ipRepository.save(ipEntity);
    }
}
