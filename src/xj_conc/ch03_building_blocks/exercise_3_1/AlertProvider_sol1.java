
package xj_conc.ch03_building_blocks.exercise_3_1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * TODO: We need to avoid the ConcurrentModification exception in Client class.
 *
 * @see Client
 *
 * GCStatistics for exercise_3_1.vgc
durationOfLog=PT21.539S
numberOfGCs=59
numberOfYoungGCs=59
numberOfOldGCs=0
memoryReclaimedDuringYoung=22.293GB
memoryReclaimedDuringOld=0.000B
maxHeapAfterGC=1.305MB
totalMemoryAllocated=22.294GB
averageCreationRate=1.04GB/s
timeInGCs=PT0.1105589S
timeInYoungGCs=PT0.1105589S
averageTimeInYoungGCs=DoubleSummaryStatistics{count=59, sum=0.110559, min=0.000492, average=0.001874, max=0.008936}
timeInOldGCs=PT0S
percentageOfTimeInGC=0.51%
 */
public class AlertProvider_sol1 {
    private final List<Alert> alerts = Collections.synchronizedList(
        new ArrayList<>(10));

    public Collection<Alert> getAlerts() {
        return Collections.unmodifiableCollection(new ArrayList<>(alerts));
    }

    private AlertProvider_sol1() {
    }

    private final static AlertProvider_sol1 instance = new AlertProvider_sol1();

    public static AlertProvider_sol1 getInstance() {
        return instance;
    }

    public boolean addAlert(Alert alert) {
        return alerts.add(alert);
    }

    public boolean removeAlert(Alert alert) {
        return alerts.remove(alert);
    }
}
