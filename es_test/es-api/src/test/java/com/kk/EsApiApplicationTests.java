package com.kk;

import com.alibaba.fastjson.JSON;
import com.kk.pojo.User;
import com.kk.utils.ESconst;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class EsApiApplicationTests {
    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    //测试索引的创建
    @Test
    void testCreateIndex() throws IOException {
        //创建索引请求
        CreateIndexRequest request = new CreateIndexRequest("kang_index");
        //执行创建请求 IndicesClient,请求后获得响应
        CreateIndexResponse createIndexRequest = client.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(createIndexRequest);
    }
    //测试获取索引 判断其是否存在
    @Test
    void testExistIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest("kang_index");
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }
    //测试删除索引
    @Test
    void testDeleteIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("kang_index");
        //删除
        AcknowledgedResponse delete = client.indices().delete(request, RequestOptions.DEFAULT);
        System.out.println(delete.isAcknowledged());
    }
    //测试添加文档
    @Test
    void testAddDoucment() throws IOException {
        //创建对象
        User user = new User("kxq", 3);
        //创建请求
        IndexRequest request = new IndexRequest("kang_index");
        //规则 put /kang_index/_doc/1
        request.id("1");
//        request.timeout(TimeValue.timeValueSeconds(1));
        request.timeout("1s");

        //将我们的数据放入请求 json
        request.source(JSON.toJSONString(user), XContentType.JSON);

        //客户端发送请求，获取响应结果
        //IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);

        System.out.println("111" + request.toString());
        System.out.println("222" + response.status());//对应我们命令返回的状态 create

    }
    //获取文档，判断是否存在get /index/_doc/1
    @Test
    void testIsExist() throws IOException {
        GetRequest request = new GetRequest("kang_index", "1");
        //不获取返回的_source的上下文了
        request.fetchSourceContext(new FetchSourceContext(false));
        request.storedFields("_none_");

        boolean exists = client.exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    //获取文档信息
    @Test
    void testGetDocument() throws IOException {
//        GetRequest getRequest = new GetRequest("kang_index", "1");
        GetRequest getRequest = new GetRequest("kang_index", "1");
        GetResponse response = client.get(getRequest, RequestOptions.DEFAULT);

        //打印文档信息
        System.out.println(response.getSourceAsString());
        //返回全部内容
        System.out.println(response);
    }

    //更新文档信息
    @Test
    void testupdateDocument() throws IOException {
        UpdateRequest request = new UpdateRequest("kang_index", "1");
        request.timeout("1s");

        User user = new User("狂神说", 28);
        request.doc(JSON.toJSONString(user), XContentType.JSON);

        UpdateResponse update = client.update(request, RequestOptions.DEFAULT);
        System.out.println(update.status());
    }

    //删除文档信息
    @Test
    void testDeleteDocument() throws IOException {
        DeleteRequest request = new DeleteRequest("kang_index", "1");
        request.timeout("1s");
        DeleteResponse delete = client.delete(request, RequestOptions.DEFAULT);
        System.out.println(delete.status());
    }

    //特殊的，真的项目一般都会批量插入数据
    @Test
    void testBulkRequest() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("10s");

        ArrayList<User> userList = new ArrayList<>();

        userList.add(new User("kxq1", 3));
        userList.add(new User("kxq2", 3));
        userList.add(new User("kxq3", 3));
        userList.add(new User("kxq4", 3));
        userList.add(new User("kxq5", 3));
        userList.add(new User("cyf1", 3));
        userList.add(new User("cyf2", 3));
        userList.add(new User("cyf3", 3));

        for (int i = 0; i < userList.size(); i++) {
            //批量更新和批量删除，就在这里该对应的请求就可以了
            bulkRequest.add(
                    new IndexRequest("kang_index")
                            .id(""+(i+1))
                            .source(JSON.toJSONString(userList.get(i)), XContentType.JSON));
        }
        BulkResponse bulk = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulk.status());
    }

    //批量删除数据
    @Test
    void testDeleteBulkRequest() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("10s");

        ArrayList<User> userList = new ArrayList<>();

        userList.add(new User("kxq1", 3));
        userList.add(new User("kxq2", 3));
        userList.add(new User("kxq3", 3));
        userList.add(new User("kxq4", 3));
        userList.add(new User("kxq5", 3));
        userList.add(new User("cyf1", 3));
        userList.add(new User("cyf2", 3));
        userList.add(new User("cyf3", 3));

        for (int i = 0; i < userList.size(); i++) {
            //批量更新和批量删除，就在这里该对应的请求就可以
            bulkRequest.add(
                    new DeleteRequest("kang_index")
                            .id("" + (i + 1))
            );

            BulkResponse bulk = client.bulk(bulkRequest, RequestOptions.DEFAULT);

            System.out.println(bulk.status());

        }
    }

    // 批量查询
    // SearchRequest 搜索请求
    // SearchSourceBuilder 条件构造
    // highlighter 构建高亮
    // TermQueryBuilder 精确查询
    // MatchAllQueryBuilder 匹配查询
    // xxx QueryBuilders 对应看到的所有命令
    @Test
    void testSearch() throws IOException {
        SearchRequest searchRequest = new SearchRequest(ESconst.ES_INDEX);
        //构建搜索条件
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //查询条件，我们可以使用QueryBuilders工具来实现
        //QueryBuilders.termQuery()精确查询
        //QueryBuilders.matchAllQuery()匹配所有
        TermQueryBuilder queryBuilder = QueryBuilders.termQuery("age", "3");
//        MatchAllQueryBuilder allQueryBuilder = QueryBuilders.matchAllQuery();
        sourceBuilder.query(queryBuilder);
        //分页
        sourceBuilder.from();
        sourceBuilder.slice();
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(searchResponse.getHits()));
        System.out.println("=============================");
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());

        }


    }
}
