public final class PerformanceUtil {
    private PerformanceUtil() {}

    // Measure performance of a task
    private static void measurePerformance(String approachName, Runnable task) {
        long startTime = System.nanoTime();
        task.run();
        long endTime = System.nanoTime();
        System.out.printf("%s took %.2f ms%n", approachName, (endTime - startTime) / 1_000_000.0);
    }
}
