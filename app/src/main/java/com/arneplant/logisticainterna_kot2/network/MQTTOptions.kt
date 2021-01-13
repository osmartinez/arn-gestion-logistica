package com.arneplant.logisticainterna_kot2.network

// Solace PubSub+ Broker Options
// Fill in your Solace Cloud PubSub+ Broker's 'MQTT Host' and 'Password' options.

// This information can be found under:

// https://console.solace.cloud/services/ -> <your-service> -> 'Connect' -> 'MQTT'

const val LEN_IPAUTOMATA = 3
const val LEN_NUMPRENSA = 1
const val LEN_IDTAREA = 10
const val LEN_PARESTAREA = 10
const val LEN_CODIGOOF = 13
const val LEN_UTILLAJE = 25
const val LEN_TALLAUTILLAJE=10
const val LEN_TALLAARTICULO = 10
const val LEN_ETIQUETA=13
const val LEN_IDORDEN = 10
const val LEN_IDOPERACION = 10
const val LEN_CODIGOARTICULO = 20
const val LEN_NOMBRECLIENTE=25
const val LEN_PARESUTILLAJE = 3
const val LEN_IDOPERARIO = 5

const val SOLACE_CLIENT_USER_NAME = ""
const val SOLACE_CLIENT_PASSWORD = ""
const val SOLACE_MQTT_HOST = "tcp://192.168.0.104:1883"

// Other options
const val SOLACE_CONNECTION_TIMEOUT = 3
const val SOLACE_CONNECTION_KEEP_ALIVE_INTERVAL = 60
const val SOLACE_CONNECTION_CLEAN_SESSION = true
const val SOLACE_CONNECTION_RECONNECT = true
