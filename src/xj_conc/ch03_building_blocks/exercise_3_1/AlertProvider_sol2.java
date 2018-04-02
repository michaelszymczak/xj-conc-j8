package xj_conc.ch03_building_blocks.exercise_3_1;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * TODO: We need to avoid the ConcurrentModification exception in Client class.
 *
 * @see Client
 * GCStatistics for exercise_3_1.vgc
durationOfLog=PT14.034S
numberOfGCs=37
numberOfYoungGCs=37
numberOfOldGCs=0
memoryReclaimedDuringYoung=22.409GB
memoryReclaimedDuringOld=0.000B
maxHeapAfterGC=1.211MB
totalMemoryAllocated=22.410GB
averageCreationRate=1.60GB/s
timeInGCs=PT0.0452546S
timeInYoungGCs=PT0.0452546S
averageTimeInYoungGCs=DoubleSummaryStatistics{count=37, sum=0.045255, min=0.000540, average=0.001223, max=0.003829}
timeInOldGCs=PT0S
percentageOfTimeInGC=0.32%

Process finished with exit code 0

 */
public class AlertProvider_sol2 {
        private final List<Alert> alerts = new CopyOnWriteArrayList<>();

    public Collection<Alert> getAlerts() {
        return Collections.unmodifiableCollection(alerts);
    }

    private AlertProvider_sol2() {
    }

    private final static AlertProvider_sol2 instance = new AlertProvider_sol2();

    public static AlertProvider_sol2 getInstance() {
        return instance;
    }

    public boolean addAlert(Alert alert) {
        return alerts.add(alert);
    }

    public boolean removeAlert(Alert alert) {
        return alerts.remove(alert);
    }
}
