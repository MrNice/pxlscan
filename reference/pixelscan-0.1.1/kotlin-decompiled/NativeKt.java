package com.mrnice.pixelscan;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: Native.kt */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001\u001a\u0012\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005*\u0004\u0018\u00010\u0006\u001a\u0012\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005*\u0004\u0018\u00010\u0006\u001a,\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\n0\u0005\"\u0004\b\u0000\u0010\n*\u0004\u0018\u00010\u00062\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H\n0\f¨\u0006\r"}, d2 = {"optStringOrNull", "", "Lorg/json/JSONObject;", "key", "toStringList", "", "Lorg/json/JSONArray;", "toIntList", "", "mapObjects", "T", "f", "Lkotlin/Function1;", "app_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NativeKt {
    public static final String optStringOrNull(JSONObject jSONObject, String key) {
        Intrinsics.checkNotNullParameter(jSONObject, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        if (jSONObject.isNull(key) || !jSONObject.has(key)) {
            return null;
        }
        String strOptString = jSONObject.optString(key);
        return strOptString.length() != 0 ? strOptString : null;
    }

    public static final List<String> toStringList(JSONArray jSONArray) {
        if (jSONArray == null) {
            return CollectionsKt.emptyList();
        }
        IntRange intRangeUntil = RangesKt.until(0, jSONArray.length());
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRangeUntil, 10));
        Iterator<Integer> it = intRangeUntil.iterator();
        while (it.hasNext()) {
            arrayList.add(jSONArray.optString(((IntIterator) it).nextInt()));
        }
        return arrayList;
    }

    public static final List<Integer> toIntList(JSONArray jSONArray) {
        if (jSONArray == null) {
            return CollectionsKt.emptyList();
        }
        IntRange intRangeUntil = RangesKt.until(0, jSONArray.length());
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRangeUntil, 10));
        Iterator<Integer> it = intRangeUntil.iterator();
        while (it.hasNext()) {
            arrayList.add(Integer.valueOf(jSONArray.optInt(((IntIterator) it).nextInt())));
        }
        return arrayList;
    }

    public static final <T> List<T> mapObjects(JSONArray jSONArray, Function1<? super JSONObject, ? extends T> f) {
        Intrinsics.checkNotNullParameter(f, "f");
        if (jSONArray == null) {
            return CollectionsKt.emptyList();
        }
        IntRange intRangeUntil = RangesKt.until(0, jSONArray.length());
        ArrayList arrayList = new ArrayList();
        Iterator<Integer> it = intRangeUntil.iterator();
        while (it.hasNext()) {
            JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(((IntIterator) it).nextInt());
            T tInvoke = jSONObjectOptJSONObject != null ? f.invoke(jSONObjectOptJSONObject) : null;
            if (tInvoke != null) {
                arrayList.add(tInvoke);
            }
        }
        return arrayList;
    }
}
