# MyMCServer Protocol
- Type: HTTP
- Answer: JSON
- Charset: ISO_8859_1
- Client chiede il token facendo il login:
    - Richiesta:
        - "GET /login HTTP/1.1"
        - "MMCS-Username: <USERNAME>"
        - "MMCS-Password: <PASSWORD>"
    - Risposta:
        {
            "return-code":<RETURN_CODE>,
            "token":<TOKEN>
        }
- Client manda un comando:
    - Richiesta:
        - "GET /command HTTP/1.1"
        - "MMCS-Token: <TOKEN>"
        - "MMCS-Command": <COMMAND>"
    - Risposta:
        {
            "return-code":<RETURN_CODE>,
            "command-output":<COMMAND_OUTPUT>
        }
- Client chiede le stats del server:
    - Richiesta:
        - "GET /stats HTTP/1.1"
        - "MMCS-Token: <TOKEN>"
    - Risposta:
        {
            "return-code":<RETURN_CODE>,
            "system-cpu-load":"<SYSTEM_CPU_LOAD>,
            "process-cpu-load":<PROCESS_CPU_LOAD>,
            "total-memory":<TOTAL_MEMORY>,
            "free-memory":<FREE_MEMORY>,
            "committed-virtual-memory":<COMMITTED_VIRTUAL_MEMORY>,
            "player-list":[
                {
                    "name":"<PLAYER_NAME>",
                    "online":true|false
                }
            ]
            "addons-list":[
                {
                    "name":"<ADDON_NAME>",
                    "enabled":true|false
                }
            ]
        }