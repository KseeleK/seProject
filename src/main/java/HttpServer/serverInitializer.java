package HttpServer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;

public class serverInitializer extends ChannelInitializer<SocketChannel> {

    private final SslContext sslCtx;

    public serverInitializer ( SslContext sslCtx ) {
        this.sslCtx = sslCtx;
    }

    @Override
    protected void initChannel ( SocketChannel ch ) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        if ( sslCtx != null ) {
            pipeline.addLast( sslCtx.newHandler( ch.alloc() ) );
        }

        pipeline.addLast( "httpServerCodec", new HttpServerCodec() );
        pipeline.addLast( new HttpUploadHandler() );

        pipeline.addLast( new HttpObjectAggregator( 65536 ) );
        pipeline.addLast( new ChunkedWriteHandler() );
        pipeline.addLast( "testHttpServerHandler", new HttpServerHandler() );

    }
}
