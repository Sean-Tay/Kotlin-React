/// <reference types="vitest" />

import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react-swc'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],

  test: {
    typecheck: {
      tsconfig: './tsconfig.vitest.json'
    },

    globals: true,
    environment: 'happy-dom',
    globalSetup: './tests/globalSetupFile.js',
    setupFiles: './tests/setupTestFile.js',

    reporters: [
      'junit',
    ],
    outputFile: {
      'junit': './reports/test/junit.xml',
    },

    coverage: {
      provider: 'istanbul',
      reporter: [
        'cobertura'
      ],
      'reportsDirectory': './reports/coverage'
    },
  }
})
