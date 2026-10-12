package com.mrnice.pixelscan;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.concurrent.ThreadsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Monitor.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/mrnice/pixelscan/MonitorJobService;", "Landroid/app/job/JobService;", "<init>", "()V", "worker", "Ljava/lang/Thread;", "onStartJob", "", "params", "Landroid/app/job/JobParameters;", "onStopJob", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MonitorJobService extends JobService {
    public static final int $stable = 8;
    private volatile Thread worker;

    @Override // android.app.job.JobService
    public boolean onStartJob(final JobParameters params) {
        Intrinsics.checkNotNullParameter(params, "params");
        this.worker = ThreadsKt.thread$default(false, false, null, null, 0, new Function0() { // from class: com.mrnice.pixelscan.MonitorJobService$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MonitorJobService.onStartJob$lambda$1(this.f$0, params);
            }
        }, 31, null);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onStartJob$lambda$1(MonitorJobService monitorJobService, JobParameters jobParameters) {
        try {
            Result.Companion companion = Result.INSTANCE;
            Monitor monitor = Monitor.INSTANCE;
            Context applicationContext = monitorJobService.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            Result.m6658constructorimpl(monitor.runOnce(applicationContext));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m6658constructorimpl(ResultKt.createFailure(th));
        }
        monitorJobService.jobFinished(jobParameters, false);
        return Unit.INSTANCE;
    }

    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters params) {
        Intrinsics.checkNotNullParameter(params, "params");
        Thread thread = this.worker;
        if (thread == null) {
            return true;
        }
        thread.interrupt();
        return true;
    }
}
