import { useState, createContext, PropsWithChildren, useEffect } from 'react'
import ServerSystemTransport from './ServerSystemTransport'

type TransportContextState = {
  initialized: boolean

  serverSystemTransport: ServerSystemTransport
}

export const TransportContext = createContext<TransportContextState>({
  initialized: false,
} as TransportContextState)

export const TransportProvider = ({ children }: PropsWithChildren) => {
  const [transportContextState, setTransportContextState] =
    useState<TransportContextState>({
      initialized: false,
    } as TransportContextState)

  useEffect(() => {
    setTransportContextState({
      initialized: true,

      serverSystemTransport: new ServerSystemTransport(),
    })
  }, [])

  return (
    <TransportContext.Provider value={transportContextState}>
      {transportContextState.initialized ? children : null}
    </TransportContext.Provider>
  )
}

export default TransportProvider
