import org.json.JSONObject;
import org.json.JSONArray;

public class SecretSharing {

    // Function to decode base-encoded value to integer
    public static int decodeBase(String value, int base) {
        return Integer.parseInt(value, base);
    }

    // Lagrange interpolation to find the constant term at x = 0
    public static double lagrangeInterpolationAt0(int[] xVals, int[] yVals) {
        double constantTerm = 0;
        int n = xVals.length;
        for (int i = 0; i < n; i++) {
            double term = yVals[i];
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    term *= (0 - xVals[j]) / (double) (xVals[i] - xVals[j]);
                }
            }
            constantTerm += term;
        }
        return constantTerm;
    }

    // Function to parse the input JSON and extract roots
    public static int[][] parseInput(JSONObject jsonData) {
        // We'll store roots in an array where each element is {x, y}
        JSONArray keys = jsonData.getJSONArray("keys");
        int[][] roots = new int[keys.length()][2];
        int index = 0;
        for (String key : jsonData.keySet()) {
            if (!key.equals("keys")) {
                JSONObject rootData = jsonData.getJSONObject(key);
                int x = Integer.parseInt(key); // x is the key
                int base = rootData.getInt("base"); // base for y value
                String yValue = rootData.getString("value"); // y value as a string
                int y = decodeBase(yValue, base); // decode the y value
                roots[index][0] = x; // store x
                roots[index][1] = y; // store y
                index++;
            }
        }
        return roots;
    }

    public static void main(String[] args) {
        // JSON input as a string
        String jsonInput1 = """
            {
                "keys": {
                    "n": 4,
                    "k": 3
                },
                "1": {
                    "base": "10",
                    "value": "4"
                },
                "2": {
                    "base": "2",
                    "value": "111"
                },
                "3": {
                    "base": "10",
                    "value": "12"
                },
                "6": {
                    "base": "4",
                    "value": "213"
                }
            }
        """;

        String jsonInput2 = """
            {
                "keys": {
                    "n": 10,
                    "k": 7
                },
                "1": {
                    "base": "6",
                    "value": "13444211440455345511"
                },
                "2": {
                    "base": "15",
                    "value": "aed7015a346d63"
                },
                "3": {
                    "base": "15",
                    "value": "6aeeb69631c227c"
                },
                "4": {
                    "base": "16",
                    "value": "e1b5e05623d881f"
                },
                "5": {
                    "base": "8",
                    "value": "316034514573652620673"
                },
                "6": {
                    "base": "3",
                    "value": "2122212201122002221120200210011020220200"
                },
                "7": {
                    "base": "13",
                    "value": "20120221122211000100210021102001201112121"
                },
                "8": {
                    "base": "6",
                    "value": "20220554335330240002224253"
                },
                "9": {
                    "base": "12",
                    "value": "45153788322a1255483"
                },
                "10": {
                    "base": "7",
                    "value": "1101613130313526312514143"
                }
            }
        """;

        // Parse the input JSON strings
        JSONObject data1 = new JSONObject(jsonInput1);
        JSONObject data2 = new JSONObject(jsonInput2);

        // Extract roots for the first test case
        int[][] roots1 = parseInput(data1);
        int[] xVals1 = new int[roots1.length];
        int[] yVals1 = new int[roots1.length];
        for (int i = 0; i < roots1.length; i++) {
            xVals1[i] = roots1[i][0];
            yVals1[i] = roots1[i][1];
        }

        // Extract roots for the second test case
        int[][] roots2 = parseInput(data2);
        int[] xVals2 = new int[roots2.length];
        int[] yVals2 = new int[roots2.length];
        for (int i = 0; i < roots2.length; i++) {
            xVals2[i] = roots2[i][0];
            yVals2[i] = roots2[i][1];
        }

        // Find the constant term (c) using Lagrange interpolation for the first test case
        double constantTerm1 = lagrangeInterpolationAt0(xVals1, yVals1);
        System.out.println("Test Case 1 - Constant term (c): " + constantTerm1);

        // Find the constant term (c) using Lagrange interpolation for the second test case
        double constantTerm2 = lagrangeInterpolationAt0(xVals2, yVals2);
        System.out.println("Test Case 2 - Constant term (c): " + constantTerm2);
 sss   }
}