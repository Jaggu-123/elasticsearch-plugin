package org.elasticsearch.plugin.aknn;

import org.elasticsearch.client.node.NodeClient;
import org.elasticsearch.common.Table;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.rest.BytesRestResponse;
import org.elasticsearch.rest.RestRequest;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.rest.action.cat.AbstractCatAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class AknnRestAction extends AbstractCatAction {
    @Override
    protected RestChannelConsumer doCatRequest(RestRequest restRequest, NodeClient nodeClient) {
        if(restRequest.path().endsWith("get")){
            return channel -> {
                try {
                    XContentBuilder builder = channel.newBuilder();
                    builder.startObject().field("getRequest", "Hii! Hello World Example Get Request").endObject();
                    channel.sendResponse(new BytesRestResponse(RestStatus.OK, builder));
                } catch (final Exception e) {
                    channel.sendResponse(new BytesRestResponse(channel, e));
                }
            };
        }
        else{
            return channel -> {
                try {
                    XContentBuilder builder = channel.newBuilder();
                    builder.startObject().field("postRequest", "Hii! Hello World Example Get Request").endObject();
                    channel.sendResponse(new BytesRestResponse(RestStatus.OK, builder));
                } catch (final Exception e) {
                    channel.sendResponse(new BytesRestResponse(channel, e));
                }
            };
        }
    }

    @Override
    protected void documentation(StringBuilder stringBuilder) {
        stringBuilder.append(documentation());
    }

    public static String documentation() {
        return "/_hello/example\n";
    }

    @Override
    protected Table getTableWithHeader(RestRequest restRequest) {
        final Table table = new Table();
        table.startHeaders();
        table.addCell("test", "desc:test");
        table.endHeaders();
        return table;
    }

    @Override
    public String getName() {
        return "rest_handler_hello_world";
    }

    @Override
    public List<Route> routes() {
        return new ArrayList<>(Arrays.asList(
                new Route(RestRequest.Method.GET, "/_hello/get"),
                new Route(RestRequest.Method.POST, "/_hello/post")
        ));
    }
}
