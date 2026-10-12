package com.mrnice.pixelscan;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Store.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B?\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\t\u0010\nJ\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003JA\u0010\u0017\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00062\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f¨\u0006\u001d"}, d2 = {"Lcom/mrnice/pixelscan/DeviceOverride;", "", "nickname", "", "typeOverride", "watched", "", "known", "note", "<init>", "(Ljava/lang/String;Ljava/lang/String;ZZLjava/lang/String;)V", "getNickname", "()Ljava/lang/String;", "getTypeOverride", "getWatched", "()Z", "getKnown", "getNote", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class DeviceOverride {
    public static final int $stable = 0;
    private final boolean known;
    private final String nickname;
    private final String note;
    private final String typeOverride;
    private final boolean watched;

    public DeviceOverride() {
        this(null, null, false, false, null, 31, null);
    }

    public static /* synthetic */ DeviceOverride copy$default(DeviceOverride deviceOverride, String str, String str2, boolean z, boolean z2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = deviceOverride.nickname;
        }
        if ((i & 2) != 0) {
            str2 = deviceOverride.typeOverride;
        }
        if ((i & 4) != 0) {
            z = deviceOverride.watched;
        }
        if ((i & 8) != 0) {
            z2 = deviceOverride.known;
        }
        if ((i & 16) != 0) {
            str3 = deviceOverride.note;
        }
        String str4 = str3;
        boolean z3 = z;
        return deviceOverride.copy(str, str2, z3, z2, str4);
    }

    /* renamed from: component1, reason: from getter */
    public final String getNickname() {
        return this.nickname;
    }

    /* renamed from: component2, reason: from getter */
    public final String getTypeOverride() {
        return this.typeOverride;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getWatched() {
        return this.watched;
    }

    /* renamed from: component4, reason: from getter */
    public final boolean getKnown() {
        return this.known;
    }

    /* renamed from: component5, reason: from getter */
    public final String getNote() {
        return this.note;
    }

    public final DeviceOverride copy(String nickname, String typeOverride, boolean watched, boolean known, String note) {
        return new DeviceOverride(nickname, typeOverride, watched, known, note);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DeviceOverride)) {
            return false;
        }
        DeviceOverride deviceOverride = (DeviceOverride) other;
        return Intrinsics.areEqual(this.nickname, deviceOverride.nickname) && Intrinsics.areEqual(this.typeOverride, deviceOverride.typeOverride) && this.watched == deviceOverride.watched && this.known == deviceOverride.known && Intrinsics.areEqual(this.note, deviceOverride.note);
    }

    public int hashCode() {
        String str = this.nickname;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.typeOverride;
        int iHashCode2 = (((((iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31) + Boolean.hashCode(this.watched)) * 31) + Boolean.hashCode(this.known)) * 31;
        String str3 = this.note;
        return iHashCode2 + (str3 != null ? str3.hashCode() : 0);
    }

    public String toString() {
        return "DeviceOverride(nickname=" + this.nickname + ", typeOverride=" + this.typeOverride + ", watched=" + this.watched + ", known=" + this.known + ", note=" + this.note + ")";
    }

    public DeviceOverride(String str, String str2, boolean z, boolean z2, String str3) {
        this.nickname = str;
        this.typeOverride = str2;
        this.watched = z;
        this.known = z2;
        this.note = str3;
    }

    public /* synthetic */ DeviceOverride(String str, String str2, boolean z, boolean z2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? false : z, (i & 8) != 0 ? false : z2, (i & 16) != 0 ? null : str3);
    }

    public final String getNickname() {
        return this.nickname;
    }

    public final String getTypeOverride() {
        return this.typeOverride;
    }

    public final boolean getWatched() {
        return this.watched;
    }

    public final boolean getKnown() {
        return this.known;
    }

    public final String getNote() {
        return this.note;
    }
}
