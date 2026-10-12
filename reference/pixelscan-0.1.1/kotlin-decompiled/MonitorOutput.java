package com.mrnice.pixelscan;

import com.mrnice.pixelscan.MonitorOutput;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: Native.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0087\b\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B+\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0003J3\u0010\u0012\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0006HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000b¨\u0006\u001a"}, d2 = {"Lcom/mrnice/pixelscan/MonitorOutput;", "", "events", "", "Lcom/mrnice/pixelscan/NetEvent;", "devicesJson", "", "onlineIds", "<init>", "(Ljava/util/List;Ljava/lang/String;Ljava/util/List;)V", "getEvents", "()Ljava/util/List;", "getDevicesJson", "()Ljava/lang/String;", "getOnlineIds", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class MonitorOutput {
    private final String devicesJson;
    private final List<NetEvent> events;
    private final List<String> onlineIds;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ MonitorOutput copy$default(MonitorOutput monitorOutput, List list, String str, List list2, int i, Object obj) {
        if ((i & 1) != 0) {
            list = monitorOutput.events;
        }
        if ((i & 2) != 0) {
            str = monitorOutput.devicesJson;
        }
        if ((i & 4) != 0) {
            list2 = monitorOutput.onlineIds;
        }
        return monitorOutput.copy(list, str, list2);
    }

    public final List<NetEvent> component1() {
        return this.events;
    }

    /* renamed from: component2, reason: from getter */
    public final String getDevicesJson() {
        return this.devicesJson;
    }

    public final List<String> component3() {
        return this.onlineIds;
    }

    public final MonitorOutput copy(List<NetEvent> events, String devicesJson, List<String> onlineIds) {
        Intrinsics.checkNotNullParameter(events, "events");
        Intrinsics.checkNotNullParameter(devicesJson, "devicesJson");
        Intrinsics.checkNotNullParameter(onlineIds, "onlineIds");
        return new MonitorOutput(events, devicesJson, onlineIds);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MonitorOutput)) {
            return false;
        }
        MonitorOutput monitorOutput = (MonitorOutput) other;
        return Intrinsics.areEqual(this.events, monitorOutput.events) && Intrinsics.areEqual(this.devicesJson, monitorOutput.devicesJson) && Intrinsics.areEqual(this.onlineIds, monitorOutput.onlineIds);
    }

    public int hashCode() {
        return (((this.events.hashCode() * 31) + this.devicesJson.hashCode()) * 31) + this.onlineIds.hashCode();
    }

    public String toString() {
        return "MonitorOutput(events=" + this.events + ", devicesJson=" + this.devicesJson + ", onlineIds=" + this.onlineIds + ")";
    }

    /* compiled from: Native.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/mrnice/pixelscan/MonitorOutput$Companion;", "", "<init>", "()V", "parse", "Lcom/mrnice/pixelscan/MonitorOutput;", "json", "", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final MonitorOutput parse(String json) {
            String string;
            Intrinsics.checkNotNullParameter(json, "json");
            try {
                JSONObject jSONObject = new JSONObject(json);
                List listMapObjects = NativeKt.mapObjects(jSONObject.optJSONArray("events"), new Function1() { // from class: com.mrnice.pixelscan.MonitorOutput$Companion$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return MonitorOutput.Companion.parse$lambda$0((JSONObject) obj);
                    }
                });
                JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("devices");
                if (jSONArrayOptJSONArray == null || (string = jSONArrayOptJSONArray.toString()) == null) {
                    string = "[]";
                }
                return new MonitorOutput(listMapObjects, string, NativeKt.toStringList(jSONObject.optJSONArray("online_ids")));
            } catch (Exception unused) {
                return new MonitorOutput(CollectionsKt.emptyList(), "[]", CollectionsKt.emptyList());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final NetEvent parse$lambda$0(JSONObject it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return NetEvent.INSTANCE.parse(it);
        }
    }

    public MonitorOutput(List<NetEvent> events, String devicesJson, List<String> onlineIds) {
        Intrinsics.checkNotNullParameter(events, "events");
        Intrinsics.checkNotNullParameter(devicesJson, "devicesJson");
        Intrinsics.checkNotNullParameter(onlineIds, "onlineIds");
        this.events = events;
        this.devicesJson = devicesJson;
        this.onlineIds = onlineIds;
    }

    public final String getDevicesJson() {
        return this.devicesJson;
    }

    public final List<NetEvent> getEvents() {
        return this.events;
    }

    public final List<String> getOnlineIds() {
        return this.onlineIds;
    }
}
