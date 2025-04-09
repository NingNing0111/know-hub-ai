import 'katex/dist/katex.min.css';
import ReactMarkdown from 'react-markdown';
import { Prism as SyntaxHighlighter } from 'react-syntax-highlighter';
import {
  duotoneDark as darkTheme,
  duotoneLight as lightTheme,
} from 'react-syntax-highlighter/dist/esm/styles/prism';
import rehypeKatex from 'rehype-katex';
import remarkMath from 'remark-math';

// Define a Recoil state for the theme

interface Props {
  content: string;
}

const MarkdownContent = (p: Props) => {
  const theme =
    localStorage.getItem('vite-ui-theme') === 'dark' ? darkTheme : lightTheme;
  return (
    <ReactMarkdown
      rehypePlugins={[rehypeKatex]}
      remarkPlugins={[remarkMath]}
      components={{
        code(props) {
          const { children, className, node, style, ref, ...rest } = props;
          const match = /language-(\w+)/.exec(className || '');
          return match ? (
            <SyntaxHighlighter
              language={match[1]} // Language extracted from className (e.g., language-js for JavaScript)
              PreTag="div"
              style={theme}
              {...rest}
            >
              {String(children).replace(/\n$/, '')}
            </SyntaxHighlighter>
          ) : (
            <code className={className} {...props}>
              {children}
            </code>
          );
        },
      }}
    >
      {p.content}
    </ReactMarkdown>
  );
};

export default MarkdownContent;
