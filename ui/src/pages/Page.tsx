import { useState, useEffect, useContext } from 'react'
import Input from 'antd/es/input/Input'

import { TransportContext } from '../transports/TransportProvider'

export function Page() {
  const { serverSystemTransport } = useContext(TransportContext)

  const [config, setConfig] = useState<string>('')
  useEffect(() => {
    serverSystemTransport
      ?.getSystemConfig()
      ?.then((config) => setConfig(JSON.stringify(config)))
  }, [])

  const onChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setConfig(e.currentTarget.value)
  }

  const onPressEnter = async () => {
    await serverSystemTransport?.postSystemConfig(JSON.parse(config))

    const savedConfig = await serverSystemTransport?.getSystemConfig()
    setConfig(JSON.stringify(savedConfig ?? ''))
  }

  return (
    <>
      <div className="card">
        <Input value={config} onChange={onChange} onPressEnter={onPressEnter} />
      </div>
    </>
  )
}
