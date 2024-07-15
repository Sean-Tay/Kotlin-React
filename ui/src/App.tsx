import { TransportProvider } from './transports/TransportProvider'
import { Page } from './pages/Page'

import './App.css'

function App() {
  return (
    <>
      <TransportProvider>
        <Page />
      </TransportProvider>
    </>
  )
}

export default App
