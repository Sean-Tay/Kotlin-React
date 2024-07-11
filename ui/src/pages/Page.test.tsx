import { describe, expect, ExpectStatic, it, vi } from 'vitest'
import { render, RenderResult, waitFor } from '@testing-library/react'
import userEvent from '@testing-library/user-event'

import { Page } from './Page'
import { TransportContext } from '../transports/TransportProvider'
import ServerSystemTransport from '../transports/ServerSystemTransport'

describe('Page', () => {
  const renderComponent = ({
    getSystemConfig = vi.fn(),
    postSystemConfig = vi.fn(),
  }: Partial<{ 
    getSystemConfig: ServerSystemTransport['getSystemConfig']
    postSystemConfig: ServerSystemTransport['postSystemConfig']
  }> = {}) => render(
    <TransportContext.Provider value={
        {
          initialized: true,
          serverSystemTransport: {
            getSystemConfig,
            postSystemConfig
          }
        }
      }
    >
      <Page />
    </TransportContext.Provider>
  )

  const getInputNode = (renderResults: RenderResult) => renderResults.baseElement.querySelector('input') as HTMLInputElement

  const assertInputValue = async (expect: ExpectStatic, renderResults: RenderResult, assertedValue: string) => {
    const inputNode = getInputNode(renderResults)

    await waitFor(() => {
      expect(inputNode).toHaveValue(assertedValue)
    })
  }

  it('should initially render with loaded config value from server, if any', async ({ expect }) => {
    const expectedPayload = { "Hello": "World" }
    const expectedPayloadStr = JSON.stringify(expectedPayload)

    const getSystemConfig = vi.fn(async () => expectedPayload)
    const renderResults = renderComponent({ getSystemConfig })

    await waitFor(() => expect(getSystemConfig).toHaveBeenCalled())
    await assertInputValue(expect, renderResults, expectedPayloadStr)
  })

  it('should submit typed config value to server, then reload', async () => {
    const inputPayload = { "Typed": "Payload" }
    const inputPayloadStr = JSON.stringify(inputPayload)

    const getSystemConfig = vi.fn(async () => ({}))
    const postSystemConfig = vi.fn(async () => inputPayload)
    const renderResults = renderComponent({ getSystemConfig, postSystemConfig })

    await waitFor(() => expect(getSystemConfig).toHaveBeenCalled())
    await assertInputValue(expect, renderResults, '{}')

    getSystemConfig.mockClear()
    getSystemConfig.mockResolvedValue(inputPayload)

    const inputNode = getInputNode(renderResults)
    await userEvent.clear(inputNode)
    await assertInputValue(expect, renderResults, '')

    await userEvent.type(inputNode, `{{"Typed": "Payload"}{enter}`)
    await assertInputValue(expect, renderResults, inputPayloadStr)

    await waitFor(() => expect(getSystemConfig).toHaveBeenCalled())
    await assertInputValue(expect, renderResults, inputPayloadStr)
  })
})