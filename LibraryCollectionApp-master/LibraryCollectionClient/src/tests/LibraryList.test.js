import React from 'react'
import { render, fireEvent, waitFor, screen } from '@testing-library/react'
import '@testing-library/jest-dom/extend-expect'
import LibraryList from '../components/LibraryList'

jest.mock('axios')

test('loads and displays libraries', async () => {
  render(<LibraryList />);
  await waitFor(() => screen.getAllByText('420 Texas Academy'));

  expect(screen.getByText('420 Texas Academy')).toHaveTextContent('420 Texas Academy');
 
})