import axios from 'axios'
import Config from '../types/Config'

export class ServerSystemTransport {
  getSystemConfig = async (): Promise<Config> =>
    (await axios.get('/api/system/config')).data

  postSystemConfig = async (config: Config): Promise<Config> =>
    (await axios.post('/api/system/config', config)).data
}

export default ServerSystemTransport
