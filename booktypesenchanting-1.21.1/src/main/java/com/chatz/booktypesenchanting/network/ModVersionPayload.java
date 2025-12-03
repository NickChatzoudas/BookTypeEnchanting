package com.chatz.booktypesenchanting.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import com.chatz.booktypesenchanting.BookTypesEnchanting;

/**
 * A simple network payload to enforce mod presence on both client and server.
 * This packet doesn't actually do anything - it just needs to exist so NeoForge
 * will check for matching network channels on both sides.
 */
public record ModVersionPayload(String version) implements CustomPacketPayload {

    public static final Type<ModVersionPayload> TYPE = new Type<>(
        ResourceLocation.fromNamespaceAndPath(BookTypesEnchanting.MODID, "version")
    );

    public static final StreamCodec<FriendlyByteBuf, ModVersionPayload> STREAM_CODEC = StreamCodec.composite(
        net.minecraft.network.codec.ByteBufCodecs.STRING_UTF8,
        ModVersionPayload::version,
        ModVersionPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}

