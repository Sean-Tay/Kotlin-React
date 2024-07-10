import { describe, it, expect } from 'vitest'
import { render } from '@testing-library/react'

import App from './App'

describe('App', () => {
  it('should render', () => {
    const renderResults = render(<App />)

    expect(renderResults.getByText('Click on the Vite and React logos to learn more')).toBeInTheDocument()
  })
})