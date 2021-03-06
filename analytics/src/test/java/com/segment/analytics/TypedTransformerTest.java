package com.segment.analytics;

import com.segment.analytics.messages.AliasMessage;
import com.segment.analytics.messages.GroupMessage;
import com.segment.analytics.messages.IdentifyMessage;
import com.segment.analytics.messages.ScreenMessage;
import com.segment.analytics.messages.TrackMessage;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TypedTransformerTest {
  @Test public void messagesFanOutCorrectly() {
    TypedTransformer transformer = mock(TypedTransformer.class);

    AliasMessage.Builder alias = AliasMessage.builder("foo").userId("bar");
    transformer.transform(alias);
    verify(transformer).alias(alias);

    GroupMessage.Builder group = GroupMessage.builder("foo").userId("bar");
    transformer.transform(group);
    verify(transformer).group(group);

    IdentifyMessage.Builder identify = IdentifyMessage.builder().userId("bar");
    transformer.transform(identify);
    verify(transformer).identify(identify);

    ScreenMessage.Builder screen = ScreenMessage.builder("foo").userId("bar");
    transformer.transform(screen);
    verify(transformer).screen(screen);

    TrackMessage.Builder track = TrackMessage.builder("foo").userId("bar");
    transformer.transform(track);
    verify(transformer).track(track);
  }
}
